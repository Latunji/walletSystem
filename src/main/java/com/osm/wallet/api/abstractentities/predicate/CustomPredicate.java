package com.osm.wallet.api.abstractentities.predicate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CustomPredicate {
    private String field;
    private Comparable value;
    private List<Comparable> otherValues;
    private Operation operation;
    private boolean negate;
    private static CustomPredicate instance;
    /**
     * Implementation is a LIFO List...
     * Capacity will not be more than 7 @ any point...
     */
    private static List<CustomPredicate> predicateList;


    /**
     *
     * @param field
     * @param value
     */

    public static CustomPredicate procurePredicate(String field, Comparable value){
        return buildCustomPredicate( field, value, Operation.EQUALS,false);
    }
    public static CustomPredicate procurePredicate(String field, Comparable value, Operation operation){
        return buildCustomPredicate( field, value, operation,false);
    }
    public static CustomPredicate procurePredicate(String field, Comparable value, Operation operation, boolean negate){
        return buildCustomPredicate( field, value, operation,negate);
    }



     public CustomPredicate(String field, Comparable value) {
        this(field, value, Operation.EQUALS);
    }


    public CustomPredicate(String field, Comparable value, Operation operation) {
        this(field, value, operation, false);
    }

    public CustomPredicate(String field, Comparable value, Operation operation, boolean negate) {
        this.field = field;
        if(operation.equals(Operation.LIKE))
            this.value = "%"+value+"%";
          else
            this.value = value;


        this.operation = operation;
        this.negate = negate;
    }
    private static CustomPredicate buildCustomPredicate(String field,Comparable comparable, Operation operation,boolean negate){


            instance = new CustomPredicate();


        if(operation.equals(Operation.LIKE))
            comparable = "%"+comparable+"%";

            instance.setField(field);
            instance.setValue(comparable);
            instance.setOperation(operation);
            instance.setNegate(negate);

        return instance;
    }
    private void setField(String field) {
        this.field = field;
    }

    private void setValue(Comparable value) {
        this.value = value;
    }

    private void setOtherValues(List<Comparable> otherValues) {
        this.otherValues = otherValues;
    }

    private void setOperation(Operation operation) {
        this.operation = operation;
    }

    private void setNegate(boolean negate) {
        this.negate = negate;
    }


}

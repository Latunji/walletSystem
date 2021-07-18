package com.osm.wallet.api.abstractentities.predicate;

import com.osm.wallet.api.payroll.utils.IppmsUtils;
import lombok.NonNull;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class PredicateBuilder {
    private final ConjunctionType conjunctionType;
    private final List<PredicateBuilder> builders;
    private final List<CustomPredicate> predicates;

    public PredicateBuilder() {
        this(ConjunctionType.AND);
    }

    public PredicateBuilder(ConjunctionType conjunctionType) {
        this.conjunctionType = conjunctionType;
        this.builders = new ArrayList<>();
        this.predicates = new ArrayList<>();
    }

    public PredicateBuilder addBuilder(PredicateBuilder builder) {
        builders.add(builder);
        return this;
    }

    public PredicateBuilder addPredicate(CustomPredicate predicate) {
        predicates.add(predicate);
        return this;
    }
    public PredicateBuilder addPredicate(@NonNull List<CustomPredicate> predicateList) {
        for(CustomPredicate c : predicateList)
            predicates.add(c);
        return this;
    }
    public <T> Predicate build(CriteriaBuilder criteriaBuilder, Root<T> root, Predicate where) {
        for (PredicateBuilder builder: builders) {
            if (conjunctionType == ConjunctionType.AND) {
                where = criteriaBuilder.and(builder.build(criteriaBuilder, root, where));
            } else {
                where = criteriaBuilder.or(builder.build(criteriaBuilder, root, where));
            }
        }
        for (CustomPredicate predicate: predicates) {
            switch (predicate.getOperation()) {
                case LIKE:
                    Predicate likePredicate = predicate.isNegate() ?
                            criteriaBuilder.notLike(criteriaBuilder.upper(getPath(predicate, root)),
                                    ((String) predicate.getValue()).toUpperCase()) :
                            criteriaBuilder.like(criteriaBuilder.upper(getPath(predicate, root)),
                                    ((String)predicate.getValue()).toUpperCase());
                    if (conjunctionType == ConjunctionType.AND) {
                        where = criteriaBuilder.and(where, likePredicate);
                    } else {
                        where = criteriaBuilder.or(where, likePredicate);
                    }

                    break;
                case EQUALS:
                    Predicate equalPredicate = predicate.isNegate() ?
                            criteriaBuilder.notEqual(getPath(predicate, root), predicate.getValue()) :
                            criteriaBuilder.equal(getPath(predicate, root), predicate.getValue());
                    if (conjunctionType == ConjunctionType.AND) {
                        where = criteriaBuilder.and(where, equalPredicate);
                    } else {
                        where = criteriaBuilder.or(where, equalPredicate);
                    }
                    break;
                case STRING_EQUALS:
                    Predicate strEqualPredicate = criteriaBuilder.equal(criteriaBuilder.upper(getPath(predicate, root)),
                            ((String)predicate.getValue()).toUpperCase());
                    if (conjunctionType == ConjunctionType.AND) {
                        where = criteriaBuilder.and(where, strEqualPredicate);
                    } else {
                        where = criteriaBuilder.or(where, strEqualPredicate);
                    }
                    break;
                case LESS:
                    /*if(Number.class.isAssignableFrom(predicate.getValue().getClass())) {
                        if (conjunctionType == ConjunctionType.AND) {

                            criteriaBuilder.and(where,
                                    criteriaBuilder.lt(getPath(predicate, root), (Number) predicate.getValue()));
                        } else {
                            criteriaBuilder.or(where,
                                    criteriaBuilder.lt(getPath(predicate, root), (Number) predicate.getValue()));
                        }
                    }else{*/
                        if (conjunctionType == ConjunctionType.AND) {

                            criteriaBuilder.and(where,
                                    criteriaBuilder.lessThan(getPath(predicate, root),  predicate.getValue()));
                        } else {
                            criteriaBuilder.or(where,
                                    criteriaBuilder.lessThan(getPath(predicate, root),  predicate.getValue()));
                        }
                  //  }

                    break;
                case LESS_OR_EQUAL:
                    if (conjunctionType == ConjunctionType.AND) {
                        criteriaBuilder.and(where,
                                criteriaBuilder.lessThanOrEqualTo(getPath(predicate, root), predicate.getValue()));
                    } else {
                        criteriaBuilder.or(where,
                                criteriaBuilder.lessThanOrEqualTo(getPath(predicate, root), predicate.getValue()));
                    }
                    break;
                case GREATER:
                    if (conjunctionType == ConjunctionType.AND) {
                        criteriaBuilder.and(where,
                            criteriaBuilder.greaterThan(getPath(predicate, root), predicate.getValue()));
                    } else {
                        criteriaBuilder.or(where,
                            criteriaBuilder.greaterThan(getPath(predicate, root), predicate.getValue()));
                    }
                    break;
                case GREATER_OR_EQUAL:
                    if (conjunctionType == ConjunctionType.AND) {
                        criteriaBuilder.and(where,
                            criteriaBuilder.greaterThanOrEqualTo(getPath(predicate, root), predicate.getValue()));
                    } else {
                        criteriaBuilder.or(where,
                            criteriaBuilder.greaterThanOrEqualTo(getPath(predicate, root), predicate.getValue()));
                    }
                    break;
                case NOT_EQUAL:
                    if (conjunctionType == ConjunctionType.AND) {
                        criteriaBuilder.and(where,
                                criteriaBuilder.notEqual(getPath(predicate, root), predicate.getValue()));
                    } else {
                        criteriaBuilder.or(where,
                                criteriaBuilder.notEqual(getPath(predicate, root), predicate.getValue()));
                    }
                    break;
                case BETWEEN:
                    Predicate betweenPredicate = predicate.isNegate() ?
                        criteriaBuilder.not(criteriaBuilder.between(getPath(predicate, root), predicate.getValue(), predicate.getOtherValues().get(0))) :
                        criteriaBuilder.between(getPath(predicate, root), predicate.getValue(), predicate.getOtherValues().get(0));
                    if (conjunctionType == ConjunctionType.AND) {
                        criteriaBuilder.and(where, betweenPredicate);
                    } else {
                        criteriaBuilder.or(where, betweenPredicate);
                    }
                    break;
                case IN:
                    CriteriaBuilder.In<Comparable> inClause = criteriaBuilder.in(getPath(predicate, root));
                    if (IppmsUtils.isNotNull(predicate.getValue()))  inClause.value(predicate.getValue());
                    for (Comparable value: predicate.getOtherValues()) inClause.value(value);
                    Predicate inPredicate = predicate.isNegate() ?
                            criteriaBuilder.not(criteriaBuilder.in(inClause)) : criteriaBuilder.in(inClause);
                    if (conjunctionType == ConjunctionType.AND) {
                        criteriaBuilder.and(where, inPredicate);
                    } else {
                        criteriaBuilder.or(where, inPredicate);
                    }
                case IS_NULL:
                    if (conjunctionType == ConjunctionType.AND) {
                        criteriaBuilder.and(where,
                                criteriaBuilder.isNull(getPath(predicate, root)));
                    } else {
                        criteriaBuilder.or(where,
                                criteriaBuilder.isNull(getPath(predicate, root)));
                    }
                    break;
            }
        }
        return where;
    }

    public static <T, X> Path<X> getPath(CustomPredicate predicate, Root<T> root) {
        return getPath(predicate.getField(), root);
    }

    public static <T, X> Path<X> getPath(String field, Root<T> root) {
        String[] paths = field.split("\\.");
        if (paths.length == 1) {
            return root.get(paths[0]);
        }
        Join join = root.join(paths[0]);
        if (paths.length > 2) {
            for (int i = 1; i < paths.length - 1; i++) {
                join = join.join(paths[i]);
            }
        }
        return join.get(paths[paths.length - 1]);
    }

    public static <T, X extends Number> Path<X> getPath(String field, Root<T> root, Class<X> clazz) {
        String[] paths = field.split("\\.");
        if (paths.length == 1) {
            return root.get(paths[0]);
        }
        Join join = root.join(paths[0]);
        if (paths.length > 2) {
            for (int i = 1; i < paths.length - 1; i++) {
                join = join.join(paths[i]);
            }
        }
        return join.get(paths[paths.length - 1]);
    }
    public  List<CustomPredicate> getPredicates(){
        return predicates;
    }
}

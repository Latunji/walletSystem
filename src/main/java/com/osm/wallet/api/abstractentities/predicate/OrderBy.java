package com.osm.wallet.api.abstractentities.predicate;

public class OrderBy {
    private String field;
    private boolean asc;

    public OrderBy(String field) {
        this(field, true);
    }

    public OrderBy(String field, boolean asc) {
        this.field = field;
        this.asc = asc;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public boolean isAsc() {
        return asc;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }
}

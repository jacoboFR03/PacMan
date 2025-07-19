package com.pacman.domain.model;

public record SortingStrategyVO(String value) {

    public static final SortingStrategyVO SALES_UNITS = new SortingStrategyVO("SALES_UNITS");
    public static final SortingStrategyVO STOCK = new SortingStrategyVO("STOCK");

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SortingStrategyVO)) return false;
        SortingStrategyVO o2 = (SortingStrategyVO) o;
        return value.equals(o2.value);
    }
}

package com.my.project.epam.milena.util;

import com.my.project.epam.milena.domain.FilterModel;

import java.util.Objects;

public class QueryBuilder {

    private final StringBuilder defaultQuery = new StringBuilder("select * from product");


    public String build(FilterModel filterModel) {

        if (Objects.isNull(filterModel)) {
            return defaultQuery.toString();
        }

        if (checkParamOnExisting(filterModel.getVolume())) {
            defaultQuery.append(whereOrAnd()).append(" volume = ").append(filterModel.getVolume());
        }
        if (checkParamOnExisting(filterModel.getColor())) {
            defaultQuery
                    .append(whereOrAnd()).append(" color = ")
                    .append("'")
                    .append(filterModel.getColor()).append("'");
        }
        if (checkParamOnExisting(filterModel.getMaxPrice())) {
            defaultQuery
                    .append(whereOrAnd()).append(" price < ").append(checkMaxPrice(filterModel.getMaxPrice()));
        }
        if (checkParamOnExisting(filterModel.getMinPrice())) {
            defaultQuery
                    .append(whereOrAnd()).append(" price > ").append(checkMinPrice(filterModel.getMinPrice()));
        }
        if (checkParamOnExisting(filterModel.getSort())) {
            defaultQuery.append(" order by ").append(filterModel.getSort());
        }
        return defaultQuery.toString();
    }

    private String whereOrAnd() {
        if (defaultQuery.toString().contains(" WHERE ")) {
            return " AND ";
        } else {
            return " WHERE ";
        }
    }

    private boolean checkParamOnExisting(String param) {
        return !Objects.isNull(param) && !param.isEmpty();
    }

    private String checkMaxPrice(String maxPrice) {
        if (maxPrice.isEmpty()) {
            return String.valueOf(Integer.MAX_VALUE);
        }
        return maxPrice;
    }

    private String checkMinPrice(String minPrice) {
        if (minPrice.isEmpty()) {
            return "0";
        }
        return minPrice;
    }
}

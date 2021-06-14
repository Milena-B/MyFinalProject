package com.my.project.epam.milena.util;

import com.my.project.epam.milena.domain.FilterModel;

import java.util.Objects;

import static com.my.project.epam.milena.util.Constants.ProductConstants.COUNT_OF_PRODUCTS;
import static com.my.project.epam.milena.util.Constants.ProductConstants.LIST_WITH_PRODUCTS;

/**
 * Represents a builder of query
 *
 * @author Milena Bocharova
 */

public class QueryBuilder {

    private final StringBuilder defaultQuery = new StringBuilder();

    public static final String GET_PRODUCTS = "SELECT product.id, product.name , product.volume, product.color, product.price,product.create_date,product.manufacturer_id,manufacturer.name AS brand FROM (product INNER JOIN manufacturer ON manufacturer.id = product.manufacturer_id)";
    public static final String GET_PRODUCTS_COUNT = "SELECT count(*) AS id FROM (product INNER JOIN manufacturer ON manufacturer.id = product.manufacturer_id)";

    /**
     * Used to build a database query
     *
     * @param filterModel is an entity that contains all the sort options on the page
     * @param query means what specific query we will build
     * @return query
     */
    public String build(FilterModel filterModel, String query) {
        if (query.equals(LIST_WITH_PRODUCTS)) {
            defaultQuery.append(GET_PRODUCTS);
        }
        if (query.equals(COUNT_OF_PRODUCTS)) {
            defaultQuery.append(GET_PRODUCTS_COUNT);
        }

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
        if (checkParamOnExisting(filterModel.getBrand())) {
            defaultQuery
                    .append(whereOrAnd()).append(" product.manufacturer_id = manufacturer.id and manufacturer.name = ")
                    .append("'")
                    .append(filterModel.getBrand()).append("'");
        }
        if (checkParamOnExisting(filterModel.getMaxPrice())) {
            defaultQuery
                    .append(whereOrAnd()).append(" price < ").append(checkMaxPrice(filterModel.getMaxPrice()));
        }
        if (checkParamOnExisting(filterModel.getMinPrice())) {
            defaultQuery
                    .append(whereOrAnd()).append(" price > ").append(checkMinPrice(filterModel.getMinPrice()));
        }
        if (query.equals("productList") && (checkParamOnExisting(filterModel.getSort()))) {
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

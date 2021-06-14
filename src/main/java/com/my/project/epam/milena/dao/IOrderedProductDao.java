package com.my.project.epam.milena.dao;

import com.my.project.epam.milena.domain.OrderedProduct;

public interface IOrderedProductDao {
    /**
     * Used to save specific product in order
     * @param orderedProduct ordered product
     * @return ordered product
     */
    OrderedProduct save(OrderedProduct orderedProduct);
}

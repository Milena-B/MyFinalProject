package com.my.project.epam.milena.dao;

import com.my.project.epam.milena.domain.OrderedProduct;

public interface IOrderedProductDao {
    OrderedProduct save(OrderedProduct orderedProduct);
}

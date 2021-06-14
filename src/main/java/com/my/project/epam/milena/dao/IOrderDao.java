package com.my.project.epam.milena.dao;

import com.my.project.epam.milena.domain.Order;

import java.util.List;

public interface IOrderDao {
    /**
     * Used to save order
     *
     * @param order order
     * @return order id
     */
    Integer save(Order order);

    /**
     * Used to get all orders
     *
     * @return list of orders
     */
    List<Order> getAllOrders();

    /**
     * Used to update order status to registered by id
     *
     * @param id int
     */
    void updateStatusToRegistered(int id);

    /**
     * Used to update order status to paid by id
     *
     * @param id int
     */
    void updateStatusToPaid(int id);

    /**
     * Used to update order status to canceled by id
     *
     * @param id int
     */
    void updateStatusToCanceled(int id);
}

package com.my.project.epam.milena.service;

import com.my.project.epam.milena.domain.Order;
import com.my.project.epam.milena.domain.OrderedProduct;

import java.util.List;

public interface IOrderService {
    /**
     * Used to do order covered transaction
     *
     * @param userId     id of the user who owns the order
     * @param address    address of the user who owns the order
     * @param cardNumber cardNumber of the user who owns the order
     * @param list       of products contained in the order
     */
    void makeOrder(int userId, String address, String cardNumber, List<OrderedProduct> list);

    /**
     * Used to get all orders
     *
     * @return list of orders
     */
    List<Order> getAll();

    /**
     * Used to update order status to registered
     *
     * @param id order id
     */
    void updateStatusRegistered(int id);

    /**
     * Used to update order status to canceled
     *
     * @param id order id
     */
    void updateStatusCanceled(int id);

    /**
     * Used to update order status to paid
     *
     * @param id order id
     */
    void updateStatusPaid(int id);
}

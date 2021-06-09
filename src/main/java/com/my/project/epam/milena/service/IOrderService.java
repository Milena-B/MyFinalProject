package com.my.project.epam.milena.service;

import com.my.project.epam.milena.domain.Order;
import com.my.project.epam.milena.domain.OrderedProduct;

import java.util.List;

public interface IOrderService {
    Integer makeOrder(int userId, String address, String cardNumber, List<OrderedProduct> list);

    List<Order> getAll();

    void updateStatusRegistered(int id);

    void updateStatusCanceled(int id);

    void updateStatusPaid(int id);
}

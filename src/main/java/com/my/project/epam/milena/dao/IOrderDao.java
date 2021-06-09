package com.my.project.epam.milena.dao;

import com.my.project.epam.milena.domain.Order;

import java.util.List;

public interface IOrderDao {
    Integer save(Order order);

    List<Order> getAllOrders();

    void updateStatusToRegistered(int id);

    void updateStatusToPaid(int id);

    void updateStatusToCanceled(int id);
}

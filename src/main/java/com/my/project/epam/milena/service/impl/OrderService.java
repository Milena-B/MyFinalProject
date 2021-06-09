package com.my.project.epam.milena.service.impl;

import com.my.project.epam.milena.dao.IOrderDao;
import com.my.project.epam.milena.dao.IOrderedProductDao;
import com.my.project.epam.milena.domain.Order;
import com.my.project.epam.milena.domain.OrderedProduct;
import com.my.project.epam.milena.service.IOrderService;
import com.my.project.epam.milena.transaction.TransactionManager;

import java.util.List;

public class OrderService implements IOrderService {

    private final TransactionManager transactionManager;
    private final IOrderDao orderDao;
    private final IOrderedProductDao orderedProductDao;

    public OrderService(final TransactionManager transactionManager, final IOrderDao orderDao, final IOrderedProductDao orderedProductDao) {
        this.transactionManager = transactionManager;
        this.orderDao = orderDao;
        this.orderedProductDao = orderedProductDao;
    }

    @Override
    public Integer makeOrder(int userId, String address, String cardNumber, List<OrderedProduct> orderedProducts) {
        return transactionManager.doGetTransactionOperation(() -> {
            Integer orderId = orderDao.save(new Order(Order.Status.REGISTERED, address, cardNumber, userId));
            orderedProducts.forEach(orderedProduct -> {
                orderedProduct.setOrderId(orderId);
                orderedProductDao.save(orderedProduct);
            });
            return orderId;
        });
    }

    @Override
    public List<Order> getAll() {
        return transactionManager.doGetTransactionOperation(orderDao::getAllOrders);
    }

    @Override
    public void updateStatusRegistered(int id) {
        transactionManager.doModifiableTransactionOperation(() -> orderDao.updateStatusToRegistered(id));
    }

    @Override
    public void updateStatusPaid(int id) {
        transactionManager.doModifiableTransactionOperation(() -> orderDao.updateStatusToPaid(id));
    }

    @Override
    public void updateStatusCanceled(int id) {
        transactionManager.doModifiableTransactionOperation(() -> orderDao.updateStatusToCanceled(id));
    }


}

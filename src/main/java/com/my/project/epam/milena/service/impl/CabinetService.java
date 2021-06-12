package com.my.project.epam.milena.service.impl;

import com.my.project.epam.milena.dao.ICabinetDao;
import com.my.project.epam.milena.domain.User;
import com.my.project.epam.milena.domain.UserOrder;
import com.my.project.epam.milena.service.ICabinetService;
import com.my.project.epam.milena.transaction.TransactionManager;

import java.util.List;

public class CabinetService implements ICabinetService {
    private final TransactionManager transactionManager;
    private final ICabinetDao cabinetDao;

    public CabinetService(final ICabinetDao cabinetDao, final TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
        this.cabinetDao = cabinetDao;
    }

    @Override
    public List<UserOrder> getAllUserOrders(User user,int offset,int limit) {
        return transactionManager.doGetTransactionOperation(() -> cabinetDao.getAllOrders(user,offset,limit));
    }
    @Override
    public int getNumberOfRecords(User user){
        return  transactionManager.doGetTransactionOperation(() ->cabinetDao.getOrdersCount(user));
    }
}

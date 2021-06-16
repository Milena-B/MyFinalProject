package com.my.project.epam.milena.service.impl;

import com.my.project.epam.milena.dao.*;
import com.my.project.epam.milena.domain.Product;
import com.my.project.epam.milena.domain.User;
import com.my.project.epam.milena.service.IWishListService;
import com.my.project.epam.milena.transaction.TransactionManager;

import java.util.List;

public class WishListService implements IWishListService {
    private final TransactionManager transactionManager;
    private final IWishListDao myFavoriteDao;

    public WishListService(final IWishListDao myFavoriteDao, final TransactionManager transactionManager) {
        this.myFavoriteDao = myFavoriteDao;
        this.transactionManager = transactionManager;
    }
    @Override
    public List<Product> getWishList(User user) {
        return transactionManager.doGetTransactionOperation(() -> myFavoriteDao.getWishList(user));
    }

}

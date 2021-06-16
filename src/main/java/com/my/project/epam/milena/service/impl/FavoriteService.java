package com.my.project.epam.milena.service.impl;

import com.my.project.epam.milena.dao.IFavoriteDao;
import com.my.project.epam.milena.domain.Favorite;
import com.my.project.epam.milena.service.IFavoriteService;
import com.my.project.epam.milena.transaction.TransactionManager;


public class FavoriteService implements IFavoriteService {

    private final IFavoriteDao favoriteDao;
    private final TransactionManager transactionManager;

    public FavoriteService(IFavoriteDao favoriteDao, TransactionManager transactionManager) {
        this.favoriteDao = favoriteDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public void save(Favorite favorite) {
        transactionManager.doModifiableTransactionOperation(() -> favoriteDao.save(favorite));
    }

}

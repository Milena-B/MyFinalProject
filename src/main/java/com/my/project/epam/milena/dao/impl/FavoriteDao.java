package com.my.project.epam.milena.dao.impl;

import com.my.project.epam.milena.dao.IFavoriteDao;
import com.my.project.epam.milena.domain.Favorite;
import com.my.project.epam.milena.exceptions.DaoException;
import com.my.project.epam.milena.util.DBUtils;
import com.my.project.epam.milena.util.JDBCConnectionHolder;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FavoriteDao implements IFavoriteDao {

    @Override
    public void save(Favorite favorite) {
        PreparedStatement preparedStatement = null;
        try {
            var count = 0;
            var connection = JDBCConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement("insert into favorites (account_id,product_id) values(?,?)");
            preparedStatement.setInt(++count, favorite.getUserId());
            preparedStatement.setInt(++count, favorite.getProductId());
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new DaoException("Cannot save favorite product");
        } finally {
            DBUtils.close(preparedStatement);
        }
    }
}



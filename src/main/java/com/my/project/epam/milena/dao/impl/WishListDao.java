package com.my.project.epam.milena.dao.impl;

import com.my.project.epam.milena.dao.IWishListDao;
import com.my.project.epam.milena.domain.Product;
import com.my.project.epam.milena.domain.User;
import com.my.project.epam.milena.exceptions.DaoException;
import com.my.project.epam.milena.util.DBUtils;
import com.my.project.epam.milena.util.JDBCConnectionHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class WishListDao implements IWishListDao {
    @Override
    public List<Product>getWishList(User user) {
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        List<Product> favoriteProducts = new ArrayList<>();
        try {
            preparedStatement = JDBCConnectionHolder.getConnection().prepareStatement("select product.name,product.color,product.price from (favorites inner join product on favorites.product_id = product.id) where account_id = "+user.getId());
            generatedKeys = preparedStatement.executeQuery();
            while (generatedKeys.next()) {
                var product = new Product();
                 product.setName(generatedKeys.getString("name"));
                 product.setColor(generatedKeys.getString("color"));
                 product.setPrice(generatedKeys.getBigDecimal("price"));
                 favoriteProducts.add(product);
            }
            return favoriteProducts;
        } catch (SQLException exception) {
            throw new DaoException("Cannot get wish list");
        } finally {
            DBUtils.close(preparedStatement, generatedKeys);
        }
    }
}

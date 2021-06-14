package com.my.project.epam.milena.dao.impl;

import com.my.project.epam.milena.dao.IOrderedProductDao;
import com.my.project.epam.milena.domain.OrderedProduct;
import com.my.project.epam.milena.exceptions.DaoException;
import com.my.project.epam.milena.util.JDBCConnectionHolder;
import com.my.project.epam.milena.util.DBUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.my.project.epam.milena.util.Constants.ErrorConstants.CAN_NOT_SAVE_ORDERED_PRODUCT_MESSAGE;
import static com.my.project.epam.milena.util.Constants.SQLConstants.*;

public class OrderedProductDao implements IOrderedProductDao {

    @Override
    public OrderedProduct save(OrderedProduct orderedProduct) {
        PreparedStatement preparedStatement = null;
        try {
            var count = 0;
            var connection = JDBCConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(SAVE_ORDERED_PRODUCT);
            preparedStatement.setInt(++count, orderedProduct.getOrderId());
            preparedStatement.setInt(++count, orderedProduct.getProductId());
            preparedStatement.setInt(++count, orderedProduct.getAmount());
            preparedStatement.setBigDecimal(++count, orderedProduct.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new DaoException(CAN_NOT_SAVE_ORDERED_PRODUCT_MESSAGE);
        } finally {
            DBUtils.close(preparedStatement);
        }
        return null;
    }
}

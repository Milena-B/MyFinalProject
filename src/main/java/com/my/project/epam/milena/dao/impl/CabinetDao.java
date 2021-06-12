package com.my.project.epam.milena.dao.impl;

import com.my.project.epam.milena.dao.ICabinetDao;
import com.my.project.epam.milena.domain.Order;
import com.my.project.epam.milena.domain.User;
import com.my.project.epam.milena.domain.UserOrder;
import com.my.project.epam.milena.exceptions.DaoException;
import com.my.project.epam.milena.util.JDBCConnectionHolder;
import com.my.project.epam.milena.util.DBUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.my.project.epam.milena.util.Constants.ErrorConstants.CAN_NOT_GET_ALL_ORDERS;
import static com.my.project.epam.milena.util.Constants.ErrorConstants.CAN_NOT_GET_ALL_PRODUCTS;
import static com.my.project.epam.milena.util.Constants.OrderConstants.*;
import static com.my.project.epam.milena.util.Constants.ProductConstants.*;
import static com.my.project.epam.milena.util.Constants.ProductConstants.ID;
import static com.my.project.epam.milena.util.Constants.SQLConstants.*;

public class CabinetDao implements ICabinetDao {

    @Override
    public List<UserOrder> getAllOrders(User user, int offset, int limit) {
        List<UserOrder> userOrders = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            preparedStatement = JDBCConnectionHolder.getConnection().prepareStatement(GET_ALL_USER_ORDERS + user.getId() + " limit ? offset ?");
            preparedStatement.setInt(1,limit);
            preparedStatement.setInt(2,offset);
            generatedKeys = preparedStatement.executeQuery();
            while (generatedKeys.next()) {
                var userOrder = new UserOrder();
                userOrder.setId(generatedKeys.getInt(ID));
                userOrder.setStatus(Order.Status.valueOf(generatedKeys.getString(STATUS)));
                userOrder.setAddress(generatedKeys.getString(ADDRESS));
                userOrder.setCardNumber(generatedKeys.getString(NUMBER));
                userOrder.setAmount(generatedKeys.getInt(AMOUNT));
                userOrder.setPrice(generatedKeys.getBigDecimal(PRICE));
                userOrder.setProductName(generatedKeys.getString(NAME));
                userOrders.add(userOrder);
            }
            return userOrders;
        } catch (SQLException exception) {
            throw new DaoException(CAN_NOT_GET_ALL_ORDERS);
        } finally {
            DBUtils.close(preparedStatement, generatedKeys);
        }
    }

    @Override
    public int getOrdersCount(User user) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        var productCount = 0;
        try {
            preparedStatement = JDBCConnectionHolder.getConnection().prepareStatement(GET_ORDERS_COUNT + user.getId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                productCount = resultSet.getInt(ID);
            }
            return productCount;
        } catch (SQLException exception) {
            throw new DaoException(CAN_NOT_GET_ALL_PRODUCTS);
        } finally {
            DBUtils.close(preparedStatement, resultSet);
        }
    }

}

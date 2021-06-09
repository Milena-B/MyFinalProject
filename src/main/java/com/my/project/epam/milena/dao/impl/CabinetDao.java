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
import static com.my.project.epam.milena.util.Constants.OrderConstants.*;
import static com.my.project.epam.milena.util.Constants.ProductConstants.*;
import static com.my.project.epam.milena.util.Constants.ProductConstants.ID;
import static com.my.project.epam.milena.util.Constants.SQLConstants.FOUND_ROWS;
import static com.my.project.epam.milena.util.Constants.SQLConstants.GET_ALL_USER_ORDERS;

public class CabinetDao implements ICabinetDao {

    private int count;

    @Override
    public List<UserOrder> getAllOrders(User user, int offset, int recordsPerPage) {
        List<UserOrder> userOrders = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            preparedStatement = JDBCConnectionHolder.getConnection().prepareStatement(GET_ALL_USER_ORDERS + user.getId() + " limit " + offset + ", " + recordsPerPage);
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
    public int getNumberOfRecords(User user) {
        List<UserOrder> userOrders = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            preparedStatement = JDBCConnectionHolder.getConnection().prepareStatement(GET_ALL_USER_ORDERS + user.getId());
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
            generatedKeys = preparedStatement.executeQuery(FOUND_ROWS);
            if (generatedKeys.next()) {
                this.count = generatedKeys.getInt(1);
            }

            return count;
        } catch (SQLException exception) {
            throw new DaoException(CAN_NOT_GET_ALL_ORDERS);
        } finally {
            DBUtils.close(preparedStatement, generatedKeys);
        }
    }

}

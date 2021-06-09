package com.my.project.epam.milena.dao.impl;

import com.my.project.epam.milena.dao.IOrderDao;
import com.my.project.epam.milena.domain.Order;
import com.my.project.epam.milena.exceptions.DaoException;
import com.my.project.epam.milena.util.JDBCConnectionHolder;
import com.my.project.epam.milena.util.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.my.project.epam.milena.util.Constants.ErrorConstants.*;
import static com.my.project.epam.milena.util.Constants.OrderConstants.*;
import static com.my.project.epam.milena.util.Constants.SQLConstants.*;

public class OrderDao implements IOrderDao {

    @Override
    public Integer save(Order order) {
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            var count = 0;
            preparedStatement = JDBCConnectionHolder.getConnection().prepareStatement(SAVE_ORDER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(++count, order.getStatus().toString());
            preparedStatement.setString(++count, order.getAddress());
            preparedStatement.setString(++count, order.getCardNumber());
            preparedStatement.setInt(++count, order.getUserId());
            preparedStatement.executeUpdate();
            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        } catch (SQLException exception) {
            throw new DaoException(CAN_NOT_SAVE_ORDER_MESSAGE);
        } finally {
            DBUtils.close(preparedStatement, generatedKeys);
        }
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        List<Order> orders = new ArrayList<>();
        try {
            preparedStatement = JDBCConnectionHolder.getConnection().prepareStatement(GET_ALL_ORDERS);
            generatedKeys = preparedStatement.executeQuery();
            while (generatedKeys.next()) {
                var order = new Order();
                order.setId(generatedKeys.getInt(ID));
                order.setStatus(Order.Status.valueOf(generatedKeys.getString(STATUS)));
                order.setAddress(generatedKeys.getString(ADDRESS));
                order.setCardNumber(generatedKeys.getString((NUMBER)));
                order.setUserId(generatedKeys.getInt(ACCOUNT_ID));
                orders.add(order);
            }
            return orders;
        } catch (SQLException exception) {
            throw new DaoException(CAN_NOT_GET_ALL_ORDERS);
        } finally {
            DBUtils.close(preparedStatement, generatedKeys);
        }
    }

    @Override
    public void updateStatusToRegistered(int id) {
        PreparedStatement preparedStatement = null;
        try {
            var connection = JDBCConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_STATUS_TO_REGISTERED);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(CAN_NOT_UPDATE_STATUS_TO_REGISTERED);
        } finally {
            DBUtils.close(preparedStatement);
        }
    }

    @Override
    public void updateStatusToCanceled(int id) {
        PreparedStatement preparedStatement = null;
        try {
            var connection = JDBCConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_STATUS_TO_CANCELED);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(CAN_NOT_UPDATE_STATUS_TO_CANCELED);
        } finally {
            DBUtils.close(preparedStatement);
        }
    }

    @Override
    public void updateStatusToPaid(int id) {
        PreparedStatement preparedStatement = null;
        try {
            var connection = JDBCConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_STATUS_TO_PAID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(CAN_NOT_UPDATE_STATUS_TO_PAID);
        } finally {
            DBUtils.close(preparedStatement);
        }
    }
}

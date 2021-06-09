package com.my.project.epam.milena.dao.impl;

import com.my.project.epam.milena.dao.IUserDao;
import com.my.project.epam.milena.encrypt.Encoder;
import com.my.project.epam.milena.exceptions.DaoException;
import com.my.project.epam.milena.util.JDBCConnectionHolder;
import com.my.project.epam.milena.domain.User;
import com.my.project.epam.milena.util.DBUtils;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.my.project.epam.milena.util.Constants.ErrorConstants.*;
import static com.my.project.epam.milena.util.Constants.SQLConstants.*;
import static com.my.project.epam.milena.util.Constants.UserConstants.*;
import static com.my.project.epam.milena.util.Constants.UserConstants.ID;

public class UserDao implements IUserDao {

    @Override
    public User save(User user) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            var count = 0;
            var connection = JDBCConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(SAVE_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(++count, user.getEmail());
            preparedStatement.setString(++count, user.getFirstName());
            preparedStatement.setString(++count, user.getLastName());
            preparedStatement.setString(++count, user.getRole().name());
            preparedStatement.setString(++count, user.getStatus().name());
            toEncrypt(user);
            preparedStatement.setString(++count, user.getPassword());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                return user;
            }
            return null;
        } catch (SQLException exception) {
            throw new DaoException(CAN_NOT_SAVE_USER_MESSAGE);
        } finally {
            DBUtils.close(preparedStatement, resultSet);
        }
    }

    private void toEncrypt(User user) {
        String password;
        try {
            password = Encoder.encrypt(user.getPassword());
            user.setPassword(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserByEmail(String email) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            var count = 0;
            var connection = JDBCConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL);
            preparedStatement.setString(++count, email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                var user = new User();
                user.setId(resultSet.getInt(ID));
                user.setFirstName(resultSet.getString(FIRST_NAME));
                user.setLastName(resultSet.getString(LAST_NAME));
                user.setEmail(resultSet.getString(EMAIL));
                user.setPassword(resultSet.getString(PASSWORD));
                user.setRole(User.Role.valueOf(resultSet.getString((ROLE))));
                user.setStatus(User.Status.valueOf(resultSet.getString(STATUS)));
                return user;
            }
        } catch (SQLException exception) {
            throw new DaoException(CAN_NOT_GET_USER_MESSAGE);
        } finally {
            DBUtils.close(preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        try {
            preparedStatement = JDBCConnectionHolder.getConnection().prepareStatement(GET_ALL_USERS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                var user = new User();
                user.setId(resultSet.getInt(ID));
                user.setFirstName(resultSet.getString(FIRST_NAME));
                user.setLastName(resultSet.getString(LAST_NAME));
                user.setEmail(resultSet.getString(EMAIL));
                user.setRole(User.Role.valueOf(resultSet.getString((ROLE))));
                user.setStatus(User.Status.valueOf(resultSet.getString(STATUS)));
                users.add(user);
            }
            return users;
        } catch (SQLException exception) {
            throw new DaoException(CAN_NOT_GET_ALL_USERS);
        } finally {
            DBUtils.close(preparedStatement, resultSet);
        }
    }

    @Override
    public void updateStatusToBlocked(String email) {
        PreparedStatement preparedStatement = null;
        try {
            var connection = JDBCConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_STATUS_TO_BLOCKED);
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(CAN_NOT_TO_BLOCK_USER);
        } finally {
            DBUtils.close(preparedStatement);
        }
    }

    @Override
    public void updateStatusToUnBlocked(String email) {
        PreparedStatement preparedStatement = null;
        try {
            var connection = JDBCConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_STATUS_TO_UNBLOCKED);
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(CAN_NOT_TO_UNBLOCK_USER);
        } finally {
            DBUtils.close(preparedStatement);
        }
    }
}
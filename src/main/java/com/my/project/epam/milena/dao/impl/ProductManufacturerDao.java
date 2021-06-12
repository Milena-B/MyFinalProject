package com.my.project.epam.milena.dao.impl;

import com.my.project.epam.milena.dao.IProductManufacturerDao;
import com.my.project.epam.milena.domain.ProductManufacturer;
import com.my.project.epam.milena.exceptions.DaoException;
import com.my.project.epam.milena.util.JDBCConnectionHolder;
import com.my.project.epam.milena.util.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.my.project.epam.milena.util.Constants.ErrorConstants.*;
import static com.my.project.epam.milena.util.Constants.ProductConstants.ID;
import static com.my.project.epam.milena.util.Constants.ProductConstants.NAME;
import static com.my.project.epam.milena.util.Constants.SQLConstants.*;

public class ProductManufacturerDao implements IProductManufacturerDao {

    @Override
    public List<ProductManufacturer> getAll() {
        List<ProductManufacturer> productManufacturers = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = JDBCConnectionHolder.getConnection().prepareStatement(GET_ALL_MANUFACTURERS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                var productManufacturer = new ProductManufacturer();
                productManufacturer.setId(resultSet.getInt(ID));
                productManufacturer.setName(resultSet.getString(NAME));
                productManufacturers.add(productManufacturer);
            }
            return productManufacturers;
        } catch (SQLException exception) {
            throw new DaoException(CAN_NOT_GET_ALL_PRODUCT_MANUFACTURES);
        } finally {
            DBUtils.close(preparedStatement, resultSet);
        }
    }

    @Override
    public ProductManufacturer getByName(String name) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            var count = 0;
            var connection = JDBCConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(GET_MANUFACTURER_BY_NAME);
            preparedStatement.setString(++count, name);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                var productManufacturer = new ProductManufacturer();
                productManufacturer.setId(resultSet.getInt(ID));
                productManufacturer.setName(resultSet.getString(NAME));
                return productManufacturer;
            }
        } catch (SQLException exception) {
            throw new DaoException(CAN_NOT_GET_MANUFACTURER_MESSAGE);
        } finally {
            DBUtils.close(preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public ProductManufacturer save(ProductManufacturer productManufacturer) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            var count = 0;
            preparedStatement = JDBCConnectionHolder.getConnection().prepareStatement(SAVE_MANUFACTURER,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(++count, productManufacturer.getName());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                productManufacturer.setId(resultSet.getInt(1));
                return productManufacturer;
            }
        } catch (SQLException exception) {
            throw new DaoException(CANNOT_SAVE_MANUFACTURER_MESSAGE);
        } finally {
            DBUtils.close(preparedStatement, resultSet);
        }

        return null;
    }

    public ProductManufacturer getManufacturerById(Integer id) {
        var productManufacturer = new ProductManufacturer();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = JDBCConnectionHolder.getConnection().prepareStatement(GET_MANUFACTURER_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                productManufacturer.setId(resultSet.getInt(ID));
                productManufacturer.setName(resultSet.getString(NAME));
                return productManufacturer;
            }
        } catch (SQLException exception) {
            throw new DaoException(CAN_NOT_GET_MANUFACTURER_BY_ID);
        } finally {
            DBUtils.close(preparedStatement, resultSet);
        }
        return null;
    }
}

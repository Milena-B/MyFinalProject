package com.my.project.epam.milena.dao.impl;

import com.my.project.epam.milena.dao.IProductDao;
import com.my.project.epam.milena.domain.Product;
import com.my.project.epam.milena.domain.ProductManufacturer;
import com.my.project.epam.milena.exceptions.DaoException;
import com.my.project.epam.milena.util.JDBCConnectionHolder;
import com.my.project.epam.milena.util.DBUtils;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.my.project.epam.milena.util.Constants.ErrorConstants.*;
import static com.my.project.epam.milena.util.Constants.ProductConstants.*;
import static com.my.project.epam.milena.util.Constants.SQLConstants.*;

public class ProductDao implements IProductDao {

    @Override
    public List<Product> getAll(String query) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Product> products = new ArrayList<>();
        try {
            preparedStatement = JDBCConnectionHolder.getConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                var product = new Product();
                product.setId(resultSet.getInt(ID));
                product.setName(resultSet.getString(NAME));
                product.setVolume(resultSet.getInt(VOLUME));
                product.setColor(resultSet.getString(COLOR));
                product.setPrice(resultSet.getBigDecimal(PRICE));
                product.setProductManufacturer(new ProductManufacturerDao().getManufacturerById(resultSet.getInt(PRODUCT_MANUFACTURER_ID)));
                product.setLocalDateTime(resultSet.getObject(CREATE_DATE, LocalDateTime.class));
                products.add(product);
            }
            return products;
        } catch (SQLException exception) {
            throw new DaoException(CAN_NOT_GET_ALL_PRODUCTS);
        } finally {
            DBUtils.close(preparedStatement, resultSet);
        }
    }

    @Override
    public Product getProductById(final Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        var product = new Product();
        try {
            preparedStatement = JDBCConnectionHolder.getConnection().prepareStatement(GET_PRODUCT_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product.setId(resultSet.getInt(ID));
                product.setName(resultSet.getString(NAME));
                product.setVolume(resultSet.getInt(VOLUME));
                product.setColor(resultSet.getString(COLOR));
                product.setPrice(resultSet.getBigDecimal(PRICE));
                product.setProductManufacturer(new ProductManufacturerDao().getManufacturerById(resultSet.getInt(PRODUCT_MANUFACTURER_ID)));
                product.setLocalDateTime(resultSet.getObject(CREATE_DATE, LocalDateTime.class));
                return product;
            }
        } catch (SQLException exception) {
            throw new DaoException(CAN_NOT_GET_PRODUCT_BY_ID);
        } finally {
            DBUtils.close(preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public void delete(Integer id) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            var connection = JDBCConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_PRODUCT);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(CAN_NOT_DELETE_PRODUCT);
        } finally {
            DBUtils.close(preparedStatement);
        }
    }

    @Override
    public Product save(Product product) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            var count = 0;
            var connection = JDBCConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(SAVE_PRODUCT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(++count, product.getName());
            preparedStatement.setInt(++count, product.getVolume());
            preparedStatement.setString(++count, product.getColor());
            preparedStatement.setBigDecimal(++count, product.getPrice());
            preparedStatement.setInt(++count, product.getProductManufacturer().getId());
            preparedStatement.setObject(++count, product.getLocalDateTime());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                product.setId(resultSet.getInt(1));
                return product;
            }
            return null;
        } catch (SQLException exception) {
            throw new DaoException(CAN_NOT_SAVE_PRODUCT_MESSAGE);
        } finally {
            DBUtils.close(preparedStatement, resultSet);
        }
    }

    @Override
    public void updateProduct(Product product) {
        PreparedStatement preparedStatement = null;
        try {
            var count = 0;
            var connection = JDBCConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_PRODUCT + product.getId());
            preparedStatement.setString(++count, product.getName());
            preparedStatement.setInt(++count, product.getVolume());
            preparedStatement.setString(++count, product.getColor());
            preparedStatement.setBigDecimal(++count, product.getPrice());
            var someProductManufacturer = new ProductManufacturerDao().getByName(product.getProductManufacturer().getName());
            if (Objects.isNull(someProductManufacturer)) {
                ProductManufacturer newManufacturer = new ProductManufacturerDao().save(product.getProductManufacturer());
                preparedStatement.setInt(++count, newManufacturer.getId());
            } else {
                int productId = someProductManufacturer.getId();
                preparedStatement.setInt(++count, productId);
            }
            preparedStatement.setObject(++count, product.getLocalDateTime());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new DaoException(CAN_NOT_SAVE_PRODUCT_MESSAGE);
        } finally {
            DBUtils.close(preparedStatement);
        }
    }

    @Override
    public List<Integer> getVolumes() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Integer> volumes = new ArrayList<>();
        try {
            preparedStatement = JDBCConnectionHolder.getConnection().prepareStatement(GET_VOLUME);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                var volume = resultSet.getInt(VOLUME);
                volumes.add(volume);
            }
            return volumes;
        } catch (SQLException exception) {
            throw new DaoException(CAN_NOT_GET_VOLUME);
        } finally {
            DBUtils.close(preparedStatement, resultSet);
        }
    }

    @Override
    public List<String> getColors() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<String> colors = new ArrayList<>();
        try {
            preparedStatement = JDBCConnectionHolder.getConnection().prepareStatement(GET_COLOR);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                var color = resultSet.getString(COLOR);
                colors.add(color);
            }
            return colors;
        } catch (SQLException exception) {
            throw new DaoException(CAN_NOT_GET_COLOR);
        } finally {
            DBUtils.close(preparedStatement, resultSet);
        }
    }

    @Override
    public List<String> getBrands() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<String> brands = new ArrayList<>();
        try {
            preparedStatement = JDBCConnectionHolder.getConnection().prepareStatement(GET_ALL_MANUFACTURERS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                var brand = resultSet.getString(NAME);
                brands.add(brand);
            }
            return brands;
        } catch (SQLException e) {
            throw new DaoException(CAN_NOT_GET_BRAND);
        } finally {
            DBUtils.close(preparedStatement, resultSet);
        }
    }
}

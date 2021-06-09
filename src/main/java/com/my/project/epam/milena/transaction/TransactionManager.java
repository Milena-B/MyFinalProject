package com.my.project.epam.milena.transaction;

import com.my.project.epam.milena.exceptions.DaoException;
import com.my.project.epam.milena.exceptions.UserException;
import com.my.project.epam.milena.util.JDBCConnectionHolder;
import com.my.project.epam.milena.util.DBUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.logging.Logger;

import static com.my.project.epam.milena.util.Constants.ErrorConstants.CAN_NOT_ROLLBACK_MESSAGE;
import static java.sql.Connection.TRANSACTION_READ_COMMITTED;

public class TransactionManager {

    private static final Logger LOGGER = Logger.getLogger(TransactionManager.class.getName());

    private final DataSource dataSource;

    public TransactionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> T doGetTransactionOperation(Supplier<T> supplier) throws UserException {
        T result = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(TRANSACTION_READ_COMMITTED);
            JDBCConnectionHolder.setConnection(connection);
            result = supplier.get();
            connection.commit();
        } catch (SQLException | DaoException externalException) {
            try {
                if (Objects.nonNull(connection)) {
                    connection.rollback();
                }
            } catch (SQLException innerException) {
                LOGGER.warning(CAN_NOT_ROLLBACK_MESSAGE);
            }
            LOGGER.warning(externalException.getMessage());
        } finally {
            JDBCConnectionHolder.removeConnection();
            DBUtils.close(connection);
        }
        return result;
    }
    public void doModifiableTransactionOperation(Runnable runnable) throws UserException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(TRANSACTION_READ_COMMITTED);
            JDBCConnectionHolder.setConnection(connection);
            runnable.run();
            connection.commit();
        }
        catch (SQLException | DaoException externalException) {
            try {
                if (Objects.nonNull(connection)) {
                    connection.rollback();
                }
            }
            catch (SQLException innerException) {
                LOGGER.warning(CAN_NOT_ROLLBACK_MESSAGE);
            }
            LOGGER.warning(externalException.getMessage());
        }
        finally {
            JDBCConnectionHolder.removeConnection();
            DBUtils.close(connection);
        }
    }
}

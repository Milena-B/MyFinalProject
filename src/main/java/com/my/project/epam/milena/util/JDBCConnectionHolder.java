package com.my.project.epam.milena.util;

import java.sql.Connection;

/**
 * Storage of connections
 *
 * @author Milena Bocharova
 */
public class JDBCConnectionHolder {

    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    private JDBCConnectionHolder() {
        throw new IllegalStateException();
    }

    /**
     * Method used to get connection
     * @return connection
     */
    public static Connection getConnection() {
        return threadLocal.get();
    }
    /**
     * Method used to set connection
     */
    public static void setConnection(Connection connection) {
        threadLocal.set(connection);
    }
    /**
     * Method used to remove connection
     */
    public static void removeConnection() {
        threadLocal.remove();
    }

}

package com.my.project.epam.milena.util;

import java.sql.Connection;

public class JDBCConnectionHolder {
    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    private JDBCConnectionHolder() {
        throw new IllegalStateException();
    }

    public static Connection getConnection() {
        return threadLocal.get();
    }

    public static void setConnection(Connection connection) {
        threadLocal.set(connection);
    }

    public static void removeConnection() {
        threadLocal.remove();
    }

}

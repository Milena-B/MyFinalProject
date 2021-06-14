package com.my.project.epam.milena.exceptions;

/**
 * Represents an exception which throws when when the error occurs in DAO
 *
 */
public class DaoException extends RuntimeException {
    public DaoException(String message) {
        super(message);
    }
}

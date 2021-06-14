package com.my.project.epam.milena.exceptions;

public class UserException extends RuntimeException {
    /**
     * Used when user doesn't exist
     *
     * @param message user doesn't exist message
     */
    public UserException(final String message) {
        super(message);
    }
}

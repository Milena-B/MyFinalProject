package com.my.project.epam.milena.util.validation;

import com.my.project.epam.milena.domain.User;

import java.util.Map;

import static com.my.project.epam.milena.util.Constants.ErrorConstants.*;
import static com.my.project.epam.milena.util.Constants.RegexConstants.VALID_EMAIL_ADDRESS_REGEX;
import static com.my.project.epam.milena.util.Constants.RegexConstants.VALID_PASSWORD_REGEX;
import static com.my.project.epam.milena.util.Constants.UserConstants.*;

public class LoginValidator {
    private final Map<String, String> errors;

    public LoginValidator(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> validate(User user) {
        if (!VALID_EMAIL_ADDRESS_REGEX.matcher(user.getEmail()).find()) {
            errors.put(LOGIN_CONFIRMATION, INCORRECT_PASSWORD_OR_EMAIL_MESSAGE);
        }
        if (!VALID_PASSWORD_REGEX.matcher(user.getPassword()).find()) {
            errors.put(LOGIN_CONFIRMATION, INCORRECT_PASSWORD_OR_EMAIL_MESSAGE);
        }
        return errors;
    }
}

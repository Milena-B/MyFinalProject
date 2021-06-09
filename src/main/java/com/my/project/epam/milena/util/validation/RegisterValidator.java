package com.my.project.epam.milena.util.validation;

import com.my.project.epam.milena.domain.User;

import java.util.Map;

import static com.my.project.epam.milena.util.Constants.ErrorConstants.*;
import static com.my.project.epam.milena.util.Constants.RegexConstants.*;
import static com.my.project.epam.milena.util.Constants.UserConstants.*;

public class RegisterValidator {

    private final Map<String, String> errors;

    public RegisterValidator(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> validate(User user) {
        if (!VALID_FIRSTNAME_AND_LASTNAME_REGEX.matcher(user.getFirstName()).find()) {
            errors.put(FIRST_NAME, WRONG_FIRST_NAME_FORMAT_MESSAGE);
            System.out.println(user.getFirstName());
        }
        if (!VALID_FIRSTNAME_AND_LASTNAME_REGEX.matcher(user.getLastName()).find()) {
            errors.put(LAST_NAME, WRONG_SECOND_NAME_FORMAT_MESSAGE);
            System.out.println(user.getLastName());
        }
        if (!VALID_EMAIL_ADDRESS_REGEX.matcher(user.getEmail()).find()) {
            errors.put(EMAIL, WRONG_EMAIL_FORMAT_MESSAGE);
        }
        if (!VALID_PASSWORD_REGEX.matcher(user.getPassword()).find()) {
            errors.put(PASSWORD, WRONG_PASSWORD_FORMAT_MESSAGE);
        }
        return errors;
    }
}

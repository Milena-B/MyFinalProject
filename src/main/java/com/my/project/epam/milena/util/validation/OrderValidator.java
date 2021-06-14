package com.my.project.epam.milena.util.validation;

import com.my.project.epam.milena.domain.Order;

import java.util.HashMap;
import java.util.Map;

import static com.my.project.epam.milena.util.Constants.ErrorConstants.WRONG_ADDRESS_FORMAT_MESSAGE;
import static com.my.project.epam.milena.util.Constants.ErrorConstants.WRONG_CARD_NUMBER_FORMAT_MESSAGE;
import static com.my.project.epam.milena.util.Constants.OrderConstants.ORDER_ADDRESS;
import static com.my.project.epam.milena.util.Constants.OrderConstants.ORDER_CARD_NUMBER;
import static com.my.project.epam.milena.util.Constants.RegexConstants.VALID_CARD_NUMBER_REGEX;

public class OrderValidator {
    private final Map<String, String> errors;

    public OrderValidator() {
        this.errors = new HashMap<>();
    }

    /**
     * Used to order validation
     *
     * @param order used to get all date about order
     * @return Map witch contains key and error message
     */

    public Map<String, String> validate(Order order) {
        if (order.getAddress().isEmpty() || order.getAddress().length() < 10) {
            errors.put(ORDER_ADDRESS, WRONG_ADDRESS_FORMAT_MESSAGE);
        }
        if (!VALID_CARD_NUMBER_REGEX.matcher(order.getCardNumber()).find()) {
            errors.put(ORDER_CARD_NUMBER, WRONG_CARD_NUMBER_FORMAT_MESSAGE);
        }
        return errors;
    }
}

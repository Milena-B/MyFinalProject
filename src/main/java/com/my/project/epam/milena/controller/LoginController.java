package com.my.project.epam.milena.controller;

import com.my.project.epam.milena.domain.User;
import com.my.project.epam.milena.exceptions.UserException;
import com.my.project.epam.milena.service.impl.UserService;
import com.my.project.epam.milena.util.validation.LoginValidator;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

import static com.my.project.epam.milena.util.Constants.AttributeConstants.*;
import static com.my.project.epam.milena.util.Constants.ErrorConstants.WRONG_PROCESS;
import static com.my.project.epam.milena.util.Constants.PathConstants.*;
import static com.my.project.epam.milena.util.Constants.UserConstants.*;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    @Serial
    private static final long serialVersionUID = 8738946777013794998L;
    private UserService userService;

    @Override
    public void init(final ServletConfig config) {
        userService = (UserService) config.getServletContext().getAttribute(USER_SERVICE_ATTRIBUTE);
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        try {
            transferFromSessionToRequest(request, response);
        } catch (ServletException | IOException e) {
            LOGGER.error(WRONG_PROCESS, e);
        }

    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        var user = fillUser(request);
        Map<String, String> errors = new HashMap<>();
        HttpSession session = request.getSession();
        errors = new LoginValidator(errors).validate(user);
        if (errors.isEmpty()) {
            try {
                user = userService.checkUserOnExistenceAndPasswordOnMatch(user);
                session.setAttribute(AUTH_USER_ATTRIBUTE, user);
            } catch (UserException exception) {
                errors.put(LOGIN_CONFIRMATION, exception.getMessage());
            }
            if (errors.isEmpty()) {
                try {
                    redirectOnPage(response);
                    return;
                } catch (IOException e) {
                    LOGGER.error(WRONG_PROCESS, e);
                }
            }
        }
        session.setAttribute(ERRORS, errors);
        session.setAttribute(USER_EMAIL_ATTRIBUTE, user.getEmail());
        try {
            response.sendRedirect(LOGIN_CONTROLLER);
        } catch (IOException e) {
            LOGGER.error(WRONG_PROCESS, e);
        }
    }

    private void redirectOnPage(HttpServletResponse response) throws IOException {
        response.sendRedirect(HOME);
    }

    private User fillUser(HttpServletRequest request) {
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        return new User(email, password);
    }

    private void transferFromSessionToRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setAttribute(ERRORS, session.getAttribute(ERRORS));
        request.setAttribute(USER_EMAIL_ATTRIBUTE, session.getAttribute(USER_EMAIL_ATTRIBUTE));
        session.removeAttribute(USER_EMAIL_ATTRIBUTE);
        session.removeAttribute(ERRORS);
        request.getRequestDispatcher(LOGIN_REGISTER_JSP).forward(request, response);
    }
}
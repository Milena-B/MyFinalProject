package com.my.project.epam.milena.controller;

import com.my.project.epam.milena.domain.User;
import com.my.project.epam.milena.service.impl.UserService;
import com.my.project.epam.milena.util.validation.RegisterValidator;
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
import java.util.Objects;

import static com.my.project.epam.milena.util.Constants.AttributeConstants.AUTH_USER_ATTRIBUTE;
import static com.my.project.epam.milena.util.Constants.AttributeConstants.USER_SERVICE_ATTRIBUTE;
import static com.my.project.epam.milena.util.Constants.ErrorConstants.EXISTING_EMAIL_MESSAGE;
import static com.my.project.epam.milena.util.Constants.ErrorConstants.WRONG_PROCESS;
import static com.my.project.epam.milena.util.Constants.PathConstants.*;
import static com.my.project.epam.milena.util.Constants.UserConstants.*;

/**
 * Registration controller.
 *
 * @author Bocharova Milena
 *
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(RegisterController.class);

    private UserService userService;

    @Serial
    private static final long serialVersionUID = 2613078138083734609L;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute(USER_SERVICE_ATTRIBUTE);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            transferFromSessionToRequest(request, response);
        } catch (ServletException | IOException e) {
            LOGGER.error(WRONG_PROCESS, e);
        }
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Map<String, String> errors = new HashMap<>();
        var user = fillUser(request);
        errors = new RegisterValidator(errors).validate(user);
        if (Objects.nonNull(checkUserOnExistingAndReturn(errors, user))) {
            session.setAttribute(AUTH_USER_ATTRIBUTE, user);
        }
        if (errors.isEmpty()) {
            try {
                response.sendRedirect(HOME);
            } catch (IOException e) {
                LOGGER.error(WRONG_PROCESS, e);
            }

        } else {
            session.setAttribute(EMAIL, user.getEmail());
            session.setAttribute(FIRST_NAME, user.getFirstName());
            session.setAttribute(LAST_NAME, user.getLastName());
            session.setAttribute(ERRORS, errors);
            try {
                response.sendRedirect(REGISTER_CONTROLLER);
            } catch (IOException e) {
                LOGGER.error(WRONG_PROCESS, e);
            }

        }
    }

    public User fillUser(HttpServletRequest request) {
        String email = request.getParameter(EMAIL);
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        String password = request.getParameter(PASSWORD);
        return new User(email, firstName, lastName, password, User.Status.UNBLOCKED, User.Role.USER);
    }

    private void transferFromSessionToRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setAttribute(ERRORS, session.getAttribute(ERRORS));
        request.setAttribute(EMAIL, session.getAttribute(EMAIL));
        request.setAttribute(FIRST_NAME, session.getAttribute(FIRST_NAME));
        request.setAttribute(LAST_NAME, session.getAttribute(LAST_NAME));
        session.removeAttribute(ERRORS);
        session.removeAttribute(EMAIL);
        session.removeAttribute(FIRST_NAME);
        session.removeAttribute(LAST_NAME);
        request.getRequestDispatcher(LOGIN_REGISTER_JSP).forward(request, response);
    }

    private User checkUserOnExistingAndReturn(Map<String, String> errors, User user) {
        if (errors.isEmpty()) {
            user = userService.saveUser(user);
            if (Objects.isNull(user)) {
                errors.put(EMAIL_CONFIRMATION, EXISTING_EMAIL_MESSAGE);
            }
        }
        return user;
    }
}
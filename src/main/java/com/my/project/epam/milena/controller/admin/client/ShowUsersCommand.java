package com.my.project.epam.milena.controller.admin.client;

import com.my.project.epam.milena.domain.User;
import com.my.project.epam.milena.service.impl.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.util.List;

import static com.my.project.epam.milena.util.Constants.AttributeConstants.*;
import static com.my.project.epam.milena.util.Constants.ErrorConstants.WRONG_PROCESS;
import static com.my.project.epam.milena.util.Constants.PathConstants.SHOW_USERS;

/**
 * Controller required to show all users in the store
 *
 * @author Bocharova Milena
 *
 */
@WebServlet("/users")
public class ShowUsersCommand extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ShowUsersCommand.class);
    @Serial
    private static final long serialVersionUID = 7500303125622666359L;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute(USER_SERVICE_ATTRIBUTE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        List<User> users = userService.getAll();
        req.setAttribute(USERS, users);
        var dispatcher = req.getRequestDispatcher(SHOW_USERS);
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            LOGGER.error(WRONG_PROCESS, e);
        }
    }
}

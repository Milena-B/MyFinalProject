package com.my.project.epam.milena.controller.admin.client;

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

import static com.my.project.epam.milena.util.Constants.AttributeConstants.USER_SERVICE_ATTRIBUTE;
import static com.my.project.epam.milena.util.Constants.ErrorConstants.WRONG_PROCESS;
import static com.my.project.epam.milena.util.Constants.PathConstants.ADMIN;
import static com.my.project.epam.milena.util.Constants.UserConstants.EMAIL;

/**
 * Controller required to unblock user by the admin
 *
 * @author Bocharova Milena
 *
 */
@WebServlet("/UnbanUser")
public class UnbanCommand extends HttpServlet {
    public static final Logger LOGGER = Logger.getLogger(UnbanCommand.class);

    @Serial
    private static final long serialVersionUID = 6252522417190004148L;
    private UserService userService;

    @Override
    public void init(final ServletConfig config) {
        userService = (UserService) config.getServletContext().getAttribute(USER_SERVICE_ATTRIBUTE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        String email = req.getParameter(EMAIL);
        userService.updateStatusToUnBlocked(email);
        try {
            resp.sendRedirect(ADMIN);
        } catch (IOException e) {
            LOGGER.error(WRONG_PROCESS, e);
        }
    }
}

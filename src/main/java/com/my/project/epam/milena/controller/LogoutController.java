package com.my.project.epam.milena.controller;

import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serial;

import static com.my.project.epam.milena.util.Constants.AttributeConstants.AUTH_USER_ATTRIBUTE;
import static com.my.project.epam.milena.util.Constants.ErrorConstants.WRONG_PROCESS;
import static com.my.project.epam.milena.util.Constants.PathConstants.LOGIN_REGISTER_JSP;

/**
 * Logout controller.
 *
 * @author Bocharova Milena
 *
 */
@WebServlet("/Logout")
public class LogoutController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(LogoutController.class);

    @Serial
    private static final long serialVersionUID = 1754093371373609974L;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        cleanSession(request);
        try {
            response.sendRedirect(LOGIN_REGISTER_JSP);
        } catch (IOException e) {
            LOGGER.error(WRONG_PROCESS, e);
        }

    }

    public void cleanSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(AUTH_USER_ATTRIBUTE);
        session.invalidate();
    }
}

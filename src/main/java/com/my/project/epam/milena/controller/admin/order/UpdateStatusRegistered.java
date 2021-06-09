package com.my.project.epam.milena.controller.admin.order;

import com.my.project.epam.milena.service.impl.OrderService;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;

import static com.my.project.epam.milena.util.Constants.AttributeConstants.ORDER_SERVICE_ATTRIBUTE;
import static com.my.project.epam.milena.util.Constants.ErrorConstants.WRONG_PROCESS;
import static com.my.project.epam.milena.util.Constants.PathConstants.ADMIN;
import static com.my.project.epam.milena.util.Constants.UserConstants.ID;

@WebServlet("/registered")
public class UpdateStatusRegistered extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(UpdateStatusRegistered.class);

    @Serial
    private static final long serialVersionUID = -8461261043707223517L;
    private OrderService orderService;

    @Override
    public void init(final ServletConfig config) {
        orderService = (OrderService) config.getServletContext().getAttribute(ORDER_SERVICE_ATTRIBUTE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            var id = Integer.parseInt(req.getParameter(ID));
            orderService.updateStatusRegistered(id);
        } catch (NumberFormatException e) {
            LOGGER.error(WRONG_PROCESS, e);
        }
        try {
            resp.sendRedirect(ADMIN);
        } catch (IOException e) {
            LOGGER.error(WRONG_PROCESS, e);
        }
    }
}


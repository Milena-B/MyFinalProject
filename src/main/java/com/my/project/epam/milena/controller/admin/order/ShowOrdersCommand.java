package com.my.project.epam.milena.controller.admin.order;


import com.my.project.epam.milena.domain.Order;
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
import java.util.List;

import static com.my.project.epam.milena.util.Constants.AttributeConstants.*;
import static com.my.project.epam.milena.util.Constants.ErrorConstants.WRONG_PROCESS;
import static com.my.project.epam.milena.util.Constants.PathConstants.ORDERS;
import static com.my.project.epam.milena.util.Constants.PathConstants.SHOW_ORDERS;

@WebServlet("/orders")
public class ShowOrdersCommand extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ShowOrdersCommand.class);

    @Serial
    private static final long serialVersionUID = -1727268340945410532L;
    private OrderService orderService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        orderService = (OrderService) config.getServletContext().getAttribute(ORDER_SERVICE_ATTRIBUTE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        List<Order> orders = orderService.getAll();
        req.setAttribute(ORDERS, orders);
        var dispatcher = req.getRequestDispatcher(SHOW_ORDERS);
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            LOGGER.error(WRONG_PROCESS, e);
        }
    }
}

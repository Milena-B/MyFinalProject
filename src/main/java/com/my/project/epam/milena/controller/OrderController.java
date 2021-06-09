package com.my.project.epam.milena.controller;

import com.my.project.epam.milena.domain.Order;
import com.my.project.epam.milena.domain.OrderedProduct;
import com.my.project.epam.milena.domain.Product;
import com.my.project.epam.milena.domain.User;
import com.my.project.epam.milena.service.IOrderService;
import com.my.project.epam.milena.service.impl.OrderService;
import com.my.project.epam.milena.util.validation.OrderValidator;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.my.project.epam.milena.util.Constants.AttributeConstants.*;
import static com.my.project.epam.milena.util.Constants.ErrorConstants.WRONG_PROCESS;
import static com.my.project.epam.milena.util.Constants.OrderConstants.ORDER_ADDRESS;
import static com.my.project.epam.milena.util.Constants.OrderConstants.ORDER_CARD_NUMBER;
import static com.my.project.epam.milena.util.Constants.PathConstants.*;
import static com.my.project.epam.milena.util.Constants.UserConstants.ERRORS;

@WebServlet("/orderController")
public class OrderController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(OrderController.class);

    @Serial
    private static final long serialVersionUID = -2736451721484120938L;
    private IOrderService orderService;

    @Override
    public void init(final ServletConfig config) {
        orderService = (OrderService) config.getServletContext().getAttribute(ORDER_SERVICE_ATTRIBUTE);
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setAttribute(ERRORS, session.getAttribute(ERRORS));
        request.setAttribute(ORDER_ADDRESS, session.getAttribute(ORDER_ADDRESS));
        request.setAttribute(ORDER_CARD_NUMBER, session.getAttribute(ORDER_CARD_NUMBER));
        session.removeAttribute(ERRORS);
        session.removeAttribute(ORDER_ADDRESS);
        session.removeAttribute(ORDER_CARD_NUMBER);
        try {
            request.getRequestDispatcher(CHECKOUT).forward(request, response);
        } catch (ServletException | IOException e) {
            LOGGER.error(WRONG_PROCESS, e);
        }
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute(CART);
        var order = fillOrder(request);
        Map<String, String> errors = new OrderValidator().validate(order);
        if (errors.isEmpty()) {
                orderService.makeOrder(getUserId(session), order.getAddress(), order.getCardNumber(), getOrderedProducts(cart));
            try {
                response.sendRedirect(SUCCESS_PAGE);
                cart.clear();
                session.removeAttribute(CART);
                session.removeAttribute(TOTAL_SUM);
            } catch (IOException e) {
                LOGGER.error(WRONG_PROCESS, e);
            }

        } else {
            session.setAttribute(ERRORS, errors);
            session.setAttribute(ORDER_ADDRESS, order.getAddress());
            session.setAttribute(ORDER_CARD_NUMBER, order.getCardNumber());
            try {
                response.sendRedirect(ORDER_CONTROLLER);
            } catch (IOException e) {
                LOGGER.error(WRONG_PROCESS, e);
            }

        }
    }

    private List<OrderedProduct> getOrderedProducts(  Map<Product, Integer> cart) {
        List<OrderedProduct> orderedProducts = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            var product = entry.getKey();
            Integer amount = entry.getValue();
            BigDecimal price = entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue()));
            var orderedProduct = new OrderedProduct(product.getId(), amount, price);
            orderedProducts.add(orderedProduct);
        }
        return orderedProducts;
    }

    private int getUserId(HttpSession session) {
        var user = (User) session.getAttribute(AUTH_USER_ATTRIBUTE);
        return user.getId();
    }

    private Order fillOrder(HttpServletRequest request) {
        return new Order(request.getParameter(ORDER_ADDRESS), request.getParameter(ORDER_CARD_NUMBER));
    }
}

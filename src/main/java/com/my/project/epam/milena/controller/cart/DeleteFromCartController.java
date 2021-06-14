package com.my.project.epam.milena.controller.cart;

import com.my.project.epam.milena.service.impl.CartService;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serial;

import static com.my.project.epam.milena.util.Constants.AttributeConstants.*;
import static com.my.project.epam.milena.util.Constants.ErrorConstants.WRONG_PROCESS;
import static com.my.project.epam.milena.util.Constants.PathConstants.CART_JSP;

/**
 * Controller for deleting from cart
 *
 * @author Bocharova Milena
 *
 */
@WebServlet("/deleteFromCart")
public class DeleteFromCartController extends CartController {
    private static final Logger LOGGER = Logger.getLogger(DeleteFromCartController.class);

    @Serial
    private static final long serialVersionUID = 8972286981029435899L;
    private CartService cartService;

    @Override
    public void init(final ServletConfig config) {
        cartService = (CartService) config.getServletContext().getAttribute(CART_SERVICE_ATTRIBUTE);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        cartService.delete(execute(request));
        session.setAttribute(CART, cartService.getCart());
        session.setAttribute(TOTAL_SUM, cartService.getTotalSum());
        try {
            response.sendRedirect(CART_JSP);
        } catch (IOException e) {
            LOGGER.error(WRONG_PROCESS, e);
        }
    }
}

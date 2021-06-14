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

/**
 * Controller for adding to cart
 *
 * @author Bocharova Milena
 *
 */
@WebServlet("/addToCart")
public class AddToCartController extends CartController {
    private static final Logger LOGGER = Logger.getLogger(AddToCartController.class);

    @Serial
    private static final long serialVersionUID = -8951493992229311526L;
    private CartService cartService;

    @Override
    public void init(final ServletConfig config) {
        cartService = (CartService) config.getServletContext().getAttribute(CART_SERVICE_ATTRIBUTE);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        cartService.add(execute(request));
        HttpSession session = request.getSession();
        session.setAttribute(CART, cartService.getCart());
        session.setAttribute(TOTAL_SUM, cartService.getTotalSum());
        try {
            response.sendRedirect(PRODUCT_LIST);
        }catch (IOException e){
            LOGGER.error(WRONG_PROCESS,e);
        }
    }
}


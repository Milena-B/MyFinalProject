package com.my.project.epam.milena.controller;

import com.my.project.epam.milena.domain.Product;
import com.my.project.epam.milena.domain.User;
import com.my.project.epam.milena.service.impl.WishListService;
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
import java.util.Objects;

import static com.my.project.epam.milena.util.Constants.AttributeConstants.*;
import static com.my.project.epam.milena.util.Constants.ErrorConstants.WRONG_PROCESS;
import static com.my.project.epam.milena.util.Constants.PathConstants.WISH_LIST_JSP;

@WebServlet("/wishList")
public class WishListController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(WishListController.class);

    @Serial
    private static final long serialVersionUID = -8799762938979866286L;
    private WishListService myFavoriteService;

    @Override
    public void init(final ServletConfig config) {
        myFavoriteService = (WishListService) config.getServletContext().getAttribute(WISH_LIST_SERVICE);
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        var user = (User) request.getSession().getAttribute(AUTH_USER_ATTRIBUTE);
        if (Objects.nonNull(user)) {
            List<Product> favorites = myFavoriteService.getWishList(user);
            request.setAttribute(WISH_LIST, favorites);
            var dispatcher = request.getRequestDispatcher(WISH_LIST_JSP);
            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                LOGGER.error(WRONG_PROCESS, e);
            }
        }
    }
}

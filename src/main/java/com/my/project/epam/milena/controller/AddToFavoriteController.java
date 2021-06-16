package com.my.project.epam.milena.controller;

import com.my.project.epam.milena.domain.Favorite;
import com.my.project.epam.milena.domain.User;
import com.my.project.epam.milena.service.impl.FavoriteService;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;

import static com.my.project.epam.milena.util.Constants.AttributeConstants.*;
import static com.my.project.epam.milena.util.Constants.ErrorConstants.WRONG_PROCESS;
import static com.my.project.epam.milena.util.Constants.ProductConstants.*;

@WebServlet("/addToFavorite")
public class AddToFavoriteController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AddToFavoriteController.class);

    @Serial
    private static final long serialVersionUID = -3878523280853888824L;
    private FavoriteService favoriteService;

    @Override
    public void init(final ServletConfig config) {
        favoriteService = (FavoriteService) config.getServletContext().getAttribute(FAVORITE_SERVICE_ATTRIBUTE);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter(ID);
        var user = (User) request.getSession().getAttribute(AUTH_USER_ATTRIBUTE);
        var favorite = new Favorite();
        try {
            favorite.setProductId(Integer.parseInt(id));
            favorite.setUserId(user.getId());
        } catch (NumberFormatException e) {
            LOGGER.error(WRONG_PROCESS, e);
        }
        favoriteService.save(favorite);
        try {
            response.sendRedirect(PRODUCT_LIST);
        } catch (IOException e) {
            LOGGER.error(WRONG_PROCESS, e);
        }
    }
}

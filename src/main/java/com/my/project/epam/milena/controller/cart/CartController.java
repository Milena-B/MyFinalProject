package com.my.project.epam.milena.controller.cart;

import com.my.project.epam.milena.domain.Product;
import com.my.project.epam.milena.domain.ProductManufacturer;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

import static com.my.project.epam.milena.util.Constants.ErrorConstants.WRONG_PROCESS;
import static com.my.project.epam.milena.util.Constants.ProductConstants.*;

public class CartController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(CartController.class);

    Product execute(final HttpServletRequest request) {
        String id = request.getParameter(ID);
        String name = request.getParameter(NAME);
        String price = request.getParameter(PRICE);
        String volume = request.getParameter(VOLUME);
        String color = request.getParameter(COLOR);
        String manufacturerId = request.getParameter(MANUFACTURER_ID);
        String manufacturerName = request.getParameter(MANUFACTURER_NAME);
        var product = new Product();
        try {
            product.setId(Integer.parseInt(id));
            product.setName(name);
            product.setPrice(new BigDecimal(price));
            product.setVolume(Integer.parseInt(volume));
            product.setColor(color);
            product.setProductManufacturer(new ProductManufacturer(Integer.parseInt(manufacturerId), manufacturerName));
        } catch (NumberFormatException e){
            LOGGER.error(WRONG_PROCESS,e);
        }
        return product;
    }
}

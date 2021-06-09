package com.my.project.epam.milena.controller.admin.product;

import com.my.project.epam.milena.domain.Product;
import com.my.project.epam.milena.domain.ProductManufacturer;
import com.my.project.epam.milena.service.impl.ProductService;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.my.project.epam.milena.util.Constants.AttributeConstants.PRODUCT_SERVICE_ATTRIBUTE;
import static com.my.project.epam.milena.util.Constants.ErrorConstants.WRONG_PROCESS;
import static com.my.project.epam.milena.util.Constants.PathConstants.ADMIN;
import static com.my.project.epam.milena.util.Constants.ProductConstants.*;

@WebServlet("/addProduct")
public class AddCommand extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AddCommand.class);

    @Serial
    private static final long serialVersionUID = 5847310018147773141L;
    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        productService = (ProductService) config.getServletContext().getAttribute(PRODUCT_SERVICE_ATTRIBUTE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter(NAME);
        try {
            var volume = Integer.parseInt(req.getParameter(VOLUME));
            String color = req.getParameter(COLOR);
            var price = new BigDecimal(req.getParameter(PRICE));
            var productManufacturer = new ProductManufacturer(req.getParameter(MANUFACTURER_NAME));
            var product = new Product(name, volume, color, price, productManufacturer, LocalDateTime.now());
            productService.saveProduct(product);
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

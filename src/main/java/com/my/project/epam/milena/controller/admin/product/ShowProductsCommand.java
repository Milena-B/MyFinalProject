package com.my.project.epam.milena.controller.admin.product;

import com.my.project.epam.milena.domain.Product;
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
import java.util.List;

import static com.my.project.epam.milena.util.Constants.AttributeConstants.*;
import static com.my.project.epam.milena.util.Constants.ErrorConstants.WRONG_PROCESS;
import static com.my.project.epam.milena.util.Constants.PathConstants.SHOW_PRODUCTS;

@WebServlet("/products")
public class ShowProductsCommand extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ShowProductsCommand.class);

    @Serial
    private static final long serialVersionUID = -6267956820708457463L;
    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        productService = (ProductService) config.getServletContext().getAttribute(PRODUCT_SERVICE_ATTRIBUTE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        List<Product> products = productService.getAllProducts();
        req.setAttribute(PRODUCTS_ATTRIBUTE, products);
        var dispatcher = req.getRequestDispatcher(SHOW_PRODUCTS);
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            LOGGER.error(WRONG_PROCESS, e);
        }
    }
}

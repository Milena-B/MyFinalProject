package com.my.project.epam.milena.controller.admin.product;

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

import static com.my.project.epam.milena.util.Constants.AttributeConstants.PRODUCT_SERVICE_ATTRIBUTE;
import static com.my.project.epam.milena.util.Constants.ErrorConstants.WRONG_PROCESS;
import static com.my.project.epam.milena.util.Constants.PathConstants.ADMIN;
import static com.my.project.epam.milena.util.Constants.UserConstants.ID;

@WebServlet("/deleteProduct")
public class DeleteCommand extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(DeleteCommand.class);

    @Serial
    private static final long serialVersionUID = 2954497631739134580L;
    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        productService = (ProductService) config.getServletContext().getAttribute(PRODUCT_SERVICE_ATTRIBUTE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Integer id = Integer.parseInt(req.getParameter(ID));
            productService.deleteById(id);
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

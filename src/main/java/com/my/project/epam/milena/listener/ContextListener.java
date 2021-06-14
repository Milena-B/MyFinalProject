package com.my.project.epam.milena.listener;

import com.my.project.epam.milena.dao.*;
import com.my.project.epam.milena.dao.impl.*;
import com.my.project.epam.milena.service.*;
import com.my.project.epam.milena.service.impl.*;
import com.my.project.epam.milena.transaction.TransactionManager;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import static com.my.project.epam.milena.util.Constants.AttributeConstants.*;
import static com.my.project.epam.milena.util.Constants.SQLConstants.OBJECT_NAME;

/**
 * Context listener.
 *
 * @author Bocharova Milena
 */
@WebListener
public class ContextListener implements ServletContextListener {
    private static final Logger LOGGER = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {

        var context = event.getServletContext();
        var transactionManager = new TransactionManager(getDataSource());

        IUserDao userDao = new UserDao();
        IUserService userService = new UserService(userDao, transactionManager);
        context.setAttribute(USER_SERVICE_ATTRIBUTE, userService);

        ICabinetDao cabinetDao = new CabinetDao();
        ICabinetService cabinetService = new CabinetService(cabinetDao, transactionManager);
        context.setAttribute(CABINET_SERVICE_ATTRIBUTE, cabinetService);

        IProductManufacturerDao productManufacturerDao = new ProductManufacturerDao();
        IProductManufacturerService productManufacturerService = new ProductManufacturerService(productManufacturerDao, transactionManager);
        context.setAttribute(PRODUCT_MANUFACTURER_SERVICE_ATTRIBUTE, productManufacturerService);

        IProductDao productDao = new ProductDao();
        IProductService productService = new ProductService(productDao, productManufacturerDao, transactionManager);
        context.setAttribute(PRODUCT_SERVICE_ATTRIBUTE, productService);

        IOrderDao orderDao = new OrderDao();
        IOrderedProductDao orderedProductDao = new OrderedProductDao();
        IOrderService orderService = new OrderService(transactionManager, orderDao, orderedProductDao,userDao);
        context.setAttribute(ORDER_SERVICE_ATTRIBUTE, orderService);

        var cartService = new CartService();
        context.setAttribute(CART_SERVICE_ATTRIBUTE, cartService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOGGER.debug("Servlet context destruction finished");
    }

    /**
     * Used to get dataSource
     *
     * @return dataSource
     */
    public DataSource getDataSource() {
        DataSource dataSource = null;
        try {
            //gets an initial context as the starting point for retrieving a DataSource object.
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup(OBJECT_NAME);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return dataSource;
    }
}
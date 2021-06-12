package com.my.project.epam.milena.controller;

import com.my.project.epam.milena.domain.FilterModel;
import com.my.project.epam.milena.domain.Product;
import com.my.project.epam.milena.service.IProductManufacturerService;
import com.my.project.epam.milena.service.IProductService;
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
import static com.my.project.epam.milena.util.Constants.ErrorConstants.*;
import static com.my.project.epam.milena.util.Constants.PathConstants.SHOP_PRODUCT_JSP;

@WebServlet("/productList")
public class ProductListController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ProductListController.class);

    @Serial
    private static final long serialVersionUID = 1945792134700590564L;
    private IProductManufacturerService productManufacturerService;
    private IProductService productService;

    @Override
    public void init(final ServletConfig config) {
        productManufacturerService = (IProductManufacturerService) config.getServletContext().getAttribute(PRODUCT_MANUFACTURER_SERVICE_ATTRIBUTE);
        productService = (IProductService) config.getServletContext().getAttribute(PRODUCT_SERVICE_ATTRIBUTE);
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute(PRODUCT_MANUFACTURER_NAME_LIST_ATTRIBUTE, productManufacturerService.getAllProductManufacturers());
        var filterModel = fillFilterModel(
                request.getParameter("sort"),
                request.getParameter("volume"),
                request.getParameter("color"),
                request.getParameter("brand"),
                request.getParameter("minPrice"),
                request.getParameter("maxPrice"));
        var page = 1;
        var limit = 8;
        if (request.getParameter(PAGE) != null) {
            try {
                page = Integer.parseInt(request.getParameter(PAGE));
            } catch (NumberFormatException e) {
                LOGGER.error(WRONG_PROCESS, e);
            }
        }

        List<Product> products = productService.getAllProductsWithFilter(filterModel, (page - 1) * limit, limit);
        int noOfRecords = productService.getNumberOfRecords(filterModel);
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / limit);
        List<Integer> volumes = productService.getVolumes();
        List<String> colors = productService.getColors();
        List<String> brands = productService.getBrands();

        request.setAttribute(PRODUCTS_ATTRIBUTE, products);
        request.setAttribute(VOLUMES_ATTRIBUTE, volumes);
        request.setAttribute(COLORS_ATTRIBUTE, colors);
        request.setAttribute(BRANDS_ATTRIBUTE, brands);

        request.setAttribute(NUMBER_OF_PAGES, noOfPages);
        request.setAttribute(CURRENT_PAGE, page);
        request.setAttribute(NUMBER_OF_RECORDS, noOfRecords);

        request.setAttribute("selectedVolume", request.getParameter("volume"));
        request.setAttribute("selectedColor",request.getParameter("color"));
        request.setAttribute("selectedBrand",request.getParameter("brand"));
        request.setAttribute("selectedSort",request.getParameter("sort"));
        request.setAttribute("selectedMinPrice",request.getParameter("minPrice"));
        request.setAttribute("selectedMaxPrice",request.getParameter("maxPrice"));

        try {
            request.getRequestDispatcher(SHOP_PRODUCT_JSP).forward(request, response);

        } catch (ServletException | IOException e) {
            LOGGER.error(WRONG_PROCESS, e);
        }
    }


    private FilterModel fillFilterModel(String sort, String volume,
                                        String color, String brand, String minPrice, String maxPrice) {

        var filterModel = new FilterModel();
        filterModel.setSort(sort);
        filterModel.setVolume(volume);
        filterModel.setColor(color);
        filterModel.setBrand(brand);
        filterModel.setMinPrice(minPrice);
        filterModel.setMaxPrice(maxPrice);

        return filterModel;
    }
}

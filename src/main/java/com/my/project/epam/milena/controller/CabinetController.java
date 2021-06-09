package com.my.project.epam.milena.controller;

import com.my.project.epam.milena.domain.*;
import com.my.project.epam.milena.service.impl.CabinetService;
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
import static com.my.project.epam.milena.util.Constants.PathConstants.CABINET_CONTROLLER;
import static com.my.project.epam.milena.util.Constants.PathConstants.MY_CABINET_JSP;

@WebServlet(CABINET_CONTROLLER)
public class CabinetController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(CabinetController.class);

    @Serial
    private static final long serialVersionUID = 4869221395636095243L;
    private CabinetService cabinetService;

    @Override
    public void init(final ServletConfig config) {
        cabinetService = (CabinetService) config.getServletContext().getAttribute(CABINET_SERVICE_ATTRIBUTE);
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        var user = (User) request.getSession().getAttribute(AUTH_USER_ATTRIBUTE);
        var page = 1;
        var recordsPerPage = 5;
        if (request.getParameter(PAGE) != null) {
            try {
                page = Integer.parseInt(request.getParameter(PAGE));
            } catch (NumberFormatException e) {
                LOGGER.error(WRONG_PROCESS, e);
            }
        }
        if (Objects.nonNull(user)) {
            List<UserOrder> userOrders = cabinetService.getAllUserOrders(user, (page - 1) * recordsPerPage, recordsPerPage);
            int noOfRecords = cabinetService.getNumberOfRecords(user);
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            request.setAttribute(USER_ORDERS_ATTRIBUTE, userOrders);
            request.setAttribute(NUMBER_OF_PAGES, noOfPages);
            request.setAttribute(CURRENT_PAGE, page);
            request.setAttribute(NUMBER_OF_RECORDS, noOfRecords);
            var dispatcher = request.getRequestDispatcher(MY_CABINET_JSP);
            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                LOGGER.error(WRONG_PROCESS, e);
            }
        }
    }
}



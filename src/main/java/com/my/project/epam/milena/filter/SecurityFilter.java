package com.my.project.epam.milena.filter;

import com.my.project.epam.milena.domain.User;
import com.my.project.epam.milena.service.impl.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.my.project.epam.milena.util.Constants.AttributeConstants.AUTH_USER_ATTRIBUTE;
import static com.my.project.epam.milena.util.Constants.AttributeConstants.USER_SERVICE_ATTRIBUTE;

/**
 * Describes a filter that separates access to pages by role
 */
public class SecurityFilter implements Filter {

    UserService userService;
    List<String> userUrls = new ArrayList<>();
    List<String> adminUrls = new ArrayList<>();

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        userService = (UserService) fConfig.getServletContext().getAttribute(USER_SERVICE_ATTRIBUTE);
        userUrls.add("/cabinet.jsp");
        userUrls.add("/checkout.jsp");
        userUrls.add("/showCabinet.jsp");
        userUrls.add("/successPage.jsp");
        adminUrls.add("/admin.jsp");
        adminUrls.add("/users");
        adminUrls.add("/UnbanUser");
        adminUrls.add("/BanUser");
        adminUrls.add("/products");
        adminUrls.add("/updateProduct");
        adminUrls.add("/addProduct");
        adminUrls.add("/deleteProduct.jsp");
        adminUrls.add("/deleteProduct");
        adminUrls.add("/showOrders.jsp");
        adminUrls.add("/showProducts.jsp");
        adminUrls.add("/showUsers.jsp");

    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (checkUsersUrls(request, response) && checkAdminUrls(request, response)) {
            filterChain.doFilter(request, response);
        }
    }

    public boolean checkUsersUrls(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var url = request.getRequestURL().toString();
        var user = (User) request.getSession().getAttribute(AUTH_USER_ATTRIBUTE);
        for (String userUrl : userUrls) {
            if (url.contains(userUrl)) {
                if (Objects.isNull(user)) {
                    response.sendRedirect("login_register.jsp");
                    return false;
                }
                String email = user.getEmail();
                var currentUser = userService.getUserByEmail(email);
                if (currentUser.getRole() != User.Role.USER) {
                    response.sendRedirect("403.jsp");
                    return false;
                }
                if (currentUser.getStatus() == User.Status.BLOCKED) {
                    response.sendRedirect("error.jsp");
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkAdminUrls(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var url = request.getRequestURL().toString();
        var user = (User) request.getSession().getAttribute(AUTH_USER_ATTRIBUTE);
        for (String adminUrl : adminUrls) {
            if (url.contains(adminUrl)) {
                if (Objects.isNull(user)) {
                    response.sendRedirect("login_register.jsp");
                    return false;
                }
                if (user.getRole() != User.Role.ADMIN) {
                    response.sendRedirect("403.jsp");
                    return false;
                }
            }
        }
        return true;
    }
}
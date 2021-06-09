package com.my.project.epam.milena.filter;

import com.my.project.epam.milena.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.my.project.epam.milena.util.Constants.AttributeConstants.AUTH_USER_ATTRIBUTE;

public class SecurityFilter implements Filter {

    List<String> userUrls = new ArrayList<>();
    List<String> adminUrls = new ArrayList<>();


    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        userUrls.add("/cabinet.jsp");
        userUrls.add("/checkout.jsp");
        adminUrls.add("/admin.jsp");
        adminUrls.add("/users");
        adminUrls.add("/UnbanUser");
        adminUrls.add("/BanUser");
        adminUrls.add("/products");
        adminUrls.add("/updateProduct");
        adminUrls.add("/addProduct");
        adminUrls.add("/deleteProduct.jsp");
        adminUrls.add("/deleteProduct");
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
                if (user.getRole() != User.Role.USER) {
                    response.sendRedirect("403.jsp");
                    return false;

                }
                if (user.getStatus() == User.Status.BLOCKED) {
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
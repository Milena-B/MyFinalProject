package com.my.project.epam.milena.dao;

import com.my.project.epam.milena.domain.User;
import com.my.project.epam.milena.domain.UserOrder;

import java.util.List;

public interface ICabinetDao {
    /**
     *
     * @param user used to get orders of a specific user
     * @param offset used to set start showing product
     * @param limit used to set limit of products on page
     * @return list of orders
     */
    List<UserOrder> getAllOrders(User user,int offset,int limit);

    /**
     *
     * @param user user
     * @return number of user orders
     */
    int getOrdersCount(User user);
}

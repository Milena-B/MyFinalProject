package com.my.project.epam.milena.service;

import com.my.project.epam.milena.domain.User;
import com.my.project.epam.milena.domain.UserOrder;

import java.util.List;

public interface ICabinetService {
    /**
     * Used to get all user orders with pagination and covered transaction
     * @param user user
     * @param offset used to set start showing product
     * @param limit means limit of products on page
     * @return list of user orders
     */
    List<UserOrder> getAllUserOrders(User user,int offset,int limit);

    /**
     * Used to get number of user orders
     * @param user user
     * @return number of records
     */
    int getNumberOfRecords(User user);
}

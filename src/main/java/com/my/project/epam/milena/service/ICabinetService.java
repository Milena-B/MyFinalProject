package com.my.project.epam.milena.service;

import com.my.project.epam.milena.domain.User;
import com.my.project.epam.milena.domain.UserOrder;

import java.util.List;

public interface ICabinetService {
    List<UserOrder> getAllUserOrders(User user,int start,int recordsPerPage);

    int getNumberOfRecords(User user);
}

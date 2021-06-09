package com.my.project.epam.milena.dao;

import com.my.project.epam.milena.domain.User;
import com.my.project.epam.milena.domain.UserOrder;

import java.util.List;

public interface ICabinetDao {
    List<UserOrder> getAllOrders(User user,int start,int recordsPerPage);

    int getNumberOfRecords(User user);
}

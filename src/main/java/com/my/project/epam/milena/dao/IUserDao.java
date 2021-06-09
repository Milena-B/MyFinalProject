package com.my.project.epam.milena.dao;

import com.my.project.epam.milena.domain.User;

import java.util.List;

public interface IUserDao {

    User save(User user);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    void updateStatusToBlocked(String email);

    void updateStatusToUnBlocked(String email);
}

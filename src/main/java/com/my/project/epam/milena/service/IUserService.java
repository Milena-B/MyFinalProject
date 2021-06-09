package com.my.project.epam.milena.service;

import com.my.project.epam.milena.domain.User;

import java.util.List;

public interface IUserService {
    User saveUser(User user);

    List<User> getAll();

    User checkUserOnExistenceAndPasswordOnMatch(User someUser);

    User getUserByEmail(String email);

    void updateStatusToBlocked(String email);

    void updateStatusToUnBlocked(String email);
}

package com.my.project.epam.milena.service;

import com.my.project.epam.milena.domain.User;

import java.util.List;

public interface IUserService {
    /**
     * Used to save user covered transaction
     *
     * @param user data
     * @return user object
     */
    User saveUser(User user);

    /**
     * Used to get all users
     * @return list of users
     */
    List<User> getAll();

    /**
     * Used to check user on existence and password match
     *
     * @param someUser used to collect transfer data
     * @return user object
     */
    User checkUserOnExistenceAndPasswordOnMatch(User someUser);

    /**
     * Used to update user status to blocked covered transaction
     * @param email string
     */
    void updateStatusToBlocked(String email);

    /**
     * Used to update user status to unblocked  covered transaction
     * @param email string
     */
    void updateStatusToUnBlocked(String email);

    /**
     * Used to get user by email
     * @param email string
     * @return user
     */
    User getUserByEmail(String email);
}

package com.my.project.epam.milena.dao;

import com.my.project.epam.milena.domain.User;

import java.util.List;

public interface IUserDao {

    /**
     * Used to save user to data base
     *
     * @param user user
     * @return user
     */
    User save(User user);

    /**
     * Used to get user by email
     *
     * @param email string
     * @return User
     */
    User getUserByEmail(String email);

    /**
     * Used to get all users
     *
     * @return users
     */
    List<User> getAllUsers();

    /**
     * Used to block user by email
     *
     * @param email string
     */
    void updateStatusToBlocked(String email);

    /**
     * Used to unblock user by email
     *
     * @param email string
     */
    void updateStatusToUnBlocked(String email);

    User getUserById(int id);
}

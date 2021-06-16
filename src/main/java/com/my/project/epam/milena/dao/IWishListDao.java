package com.my.project.epam.milena.dao;

import com.my.project.epam.milena.domain.Product;
import com.my.project.epam.milena.domain.User;

import java.util.List;

public interface IWishListDao {
    List<Product> getWishList(User user);
}

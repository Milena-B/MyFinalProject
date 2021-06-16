package com.my.project.epam.milena.service;

import com.my.project.epam.milena.domain.Product;
import com.my.project.epam.milena.domain.User;

import java.util.List;

public interface IWishListService {
    List<Product> getWishList(User user);
}

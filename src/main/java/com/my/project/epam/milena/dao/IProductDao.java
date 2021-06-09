package com.my.project.epam.milena.dao;

import com.my.project.epam.milena.domain.Product;

import java.util.List;

public interface IProductDao {

    List<Product> getAll(String query);

    Product getProductById(Integer id);

    Product save(Product product);

    void updateProduct(Product product);

    void delete(Integer id);

    List<Integer> getVolumes();

    List<String> getColors();

    List<String> getBrands();

}

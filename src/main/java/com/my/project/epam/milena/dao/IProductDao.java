package com.my.project.epam.milena.dao;

import com.my.project.epam.milena.domain.Product;

import java.util.List;

public interface IProductDao {

    List<Product> getAllProductsWithFilter(String query,int offset,int limit);

    int getProductCount(String query);

    List<Product> getAllProducts();

    Product save(Product product);

    void updateProduct(Product product);

    void delete(Integer id);

    List<Integer> getVolumes();

    List<String> getColors();

    List<String> getBrands();

}

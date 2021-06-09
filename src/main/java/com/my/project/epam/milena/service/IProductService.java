package com.my.project.epam.milena.service;

import com.my.project.epam.milena.domain.FilterModel;
import com.my.project.epam.milena.domain.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts(FilterModel filterModel);

    Product getProductById(final Integer id);

    Product saveProduct(Product product);

    void update(Product product);

    void deleteById(Integer id);

    List<Integer> getVolumes();

    List<String> getColors();

    List<String> getBrands();

}

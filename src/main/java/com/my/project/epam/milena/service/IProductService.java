package com.my.project.epam.milena.service;

import com.my.project.epam.milena.domain.FilterModel;
import com.my.project.epam.milena.domain.Product;

import java.util.List;

public interface IProductService {
    /**
     * Used to get all filtered products with pagination and  covered transaction
     *
     * @param filterModel used to get data
     * @param start used to set start showing product
     * @param  limit means limit of products on page
     * @return list of products
     */
    List<Product> getAllProductsWithFilter(FilterModel filterModel, int start, int limit);

    /**
     * Used to get all products covered transaction
     *
     * @return list of products
     */
    List<Product> getAllProducts();

    /**
     * Used to get number of records
     *
     * @param filterModel used to get data
     * @return number of records
     */
    int getNumberOfRecords(FilterModel filterModel);

    /**
     * Used to save product covered transaction
     *
     * @param product product
     * @return product object
     */
    Product saveProduct(Product product);

    /**
     * Used to update product data
     *
     * @param product product
     */
    void update(Product product);

    /**
     * Used to delete product by id
     *
     * @param id integer
     */
    void deleteById(Integer id);

    /**
     * Used to get all volumes
     *
     * @return list of volumes
     */
    List<Integer> getVolumes();

    /**
     * Used to get all colors
     *
     * @return list of colors
     */
    List<String> getColors();

    /**
     * Used to get all brands
     *
     * @return list of brands
     */
    List<String> getBrands();

}

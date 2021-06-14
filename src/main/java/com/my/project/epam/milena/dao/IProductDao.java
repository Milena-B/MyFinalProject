package com.my.project.epam.milena.dao;

import com.my.project.epam.milena.domain.Product;

import java.util.List;

public interface IProductDao {

    /**
     * @param query used to get all products by filter query
     * @param offset used to set start showing product
     * @param limit used to set limit of products on page
     * @return list of products
     */
    List<Product> getAllProductsWithFilter(String query,int offset,int limit);

    /**
     * Used to get count of all products bu filter query
     *
     * @param query database query
     * @return integer, product count
     */
    int getProductCount(String query);

    /**
     * Used to get all products
     * @return list of products
     */
    List<Product> getAllProducts();

    /**
     * Used to save product
     * @param product product
     * @return saved product
     */
    Product save(Product product);

    /**
     * Used to update product data in database
     * @param product product
     */
    void updateProduct(Product product);

    /**
     * Used to delete product from database by id
     * @param id int
     */
    void delete(Integer id);

    /**
     * Used to get all volumes from database
     * @return list of volumes
     */
    List<Integer> getVolumes();

    /**
     * Used to get all colors from database
     * @return list of colors
     */
    List<String> getColors();

    /**
     * Used to get all brands from database
     * @return list of brands
     */
    List<String> getBrands();

}

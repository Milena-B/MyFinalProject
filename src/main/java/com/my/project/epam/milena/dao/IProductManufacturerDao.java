package com.my.project.epam.milena.dao;

import com.my.project.epam.milena.domain.ProductManufacturer;

import java.util.List;

public interface IProductManufacturerDao {
     /**
      * Used to get all manufacturers of a product
      * @return list of manufacturers
      */
     List<ProductManufacturer> getAll();

     /**
      * Used to get manufacturer by name
      * @param name string
      * @return manufacturer
      */
     ProductManufacturer getByName(String name);

     /**
      * Used to save manufacturer
      * @param productManufacturer productManufacturer
      * @return manufacturer
      */
     ProductManufacturer save(ProductManufacturer productManufacturer);
}

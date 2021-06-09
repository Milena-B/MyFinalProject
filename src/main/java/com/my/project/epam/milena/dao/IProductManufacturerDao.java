package com.my.project.epam.milena.dao;

import com.my.project.epam.milena.domain.ProductManufacturer;

import java.util.List;

public interface IProductManufacturerDao {
     List<ProductManufacturer> getAll();

     ProductManufacturer getByName(String name);

     ProductManufacturer save(ProductManufacturer productManufacturer);
}

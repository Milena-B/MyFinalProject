package com.my.project.epam.milena.service;

import com.my.project.epam.milena.domain.ProductManufacturer;

import java.util.List;

public interface IProductManufacturerService {
    /**
     * Used to get all product manufacturers covered transaction
     *
     * @return list of manufacturers
     */
    List<ProductManufacturer> getAllProductManufacturers();
}

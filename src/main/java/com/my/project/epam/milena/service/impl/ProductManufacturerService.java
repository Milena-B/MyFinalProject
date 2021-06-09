package com.my.project.epam.milena.service.impl;

import com.my.project.epam.milena.dao.IProductManufacturerDao;
import com.my.project.epam.milena.domain.ProductManufacturer;
import com.my.project.epam.milena.service.IProductManufacturerService;
import com.my.project.epam.milena.transaction.TransactionManager;

import java.util.List;

public class ProductManufacturerService implements IProductManufacturerService {

    private final IProductManufacturerDao productManufacturerDao;
    private final TransactionManager transactionManager;

    public ProductManufacturerService(final IProductManufacturerDao productManufacturerDao,
                                      final TransactionManager transactionManager) {
        this.productManufacturerDao = productManufacturerDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public List<ProductManufacturer> getAllProductManufacturers() {
        return transactionManager.doGetTransactionOperation(productManufacturerDao::getAll);
    }
}

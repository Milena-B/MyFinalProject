package com.my.project.epam.milena.service.impl;

import com.my.project.epam.milena.dao.IProductDao;
import com.my.project.epam.milena.dao.IProductManufacturerDao;
import com.my.project.epam.milena.domain.FilterModel;
import com.my.project.epam.milena.domain.Product;
import com.my.project.epam.milena.service.IProductService;
import com.my.project.epam.milena.transaction.TransactionManager;
import com.my.project.epam.milena.util.QueryBuilder;

import java.util.List;
import java.util.Objects;

public class ProductService implements IProductService {

    private final IProductDao productDao;
    private final IProductManufacturerDao productManufacturerDao;
    private final TransactionManager transactionManager;


    public ProductService(final IProductDao productDao, final IProductManufacturerDao productManufacturerDao,
                          final TransactionManager transactionManager) {
        this.productDao = productDao;
        this.productManufacturerDao = productManufacturerDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public Product saveProduct(Product product) {
        return transactionManager.doGetTransactionOperation(() -> {
            var productManufacturer = productManufacturerDao.getByName(product.getProductManufacturer().getName());
            if (Objects.isNull(productManufacturer)) {
                productManufacturer = productManufacturerDao.save(product.getProductManufacturer());
            }
            product.setProductManufacturer(productManufacturer);
            return productDao.save(product);
        });
    }

    @Override
    public void update(Product product) {
        transactionManager.doModifiableTransactionOperation(() -> productDao.updateProduct(product));
    }

    @Override
    public void deleteById(Integer id) {
        transactionManager.doModifiableTransactionOperation(() -> productDao.delete(id));
    }

    @Override
    public List<Product> getAllProducts(FilterModel filterModel) {
        String query = new QueryBuilder().build(filterModel);
        return transactionManager.doGetTransactionOperation(()-> productDao.getAll(query));
    }


    @Override
    public Product getProductById(final Integer id) {
        return transactionManager.doGetTransactionOperation(() -> productDao.getProductById(id));
    }

    @Override
    public List<Integer> getVolumes(){
        return  transactionManager.doGetTransactionOperation(productDao::getVolumes);
    }
    @Override
    public List<String> getColors(){
        return  transactionManager.doGetTransactionOperation(productDao::getColors);
    }

    @Override
    public List<String> getBrands(){
        return transactionManager.doGetTransactionOperation(productDao::getBrands);
    }

}

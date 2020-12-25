package com.javaschool.onlineshop.service.impl;

import com.javaschool.onlineshop.dao.ProductDAO;
import com.javaschool.onlineshop.entity.Product;
import com.javaschool.onlineshop.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    @Transactional
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    @Transactional
    public Product getProductById(Long id) {
        return productDAO.getProductById(id);
    }

    @Override
    @Transactional
    public void addProduct(Product product) {
        productDAO.addProduct(product);

    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        productDAO.updateProduct(product);

    }

    @Override
    @Transactional
    public void deleteProduct(Product product) {
        productDAO.deleteProduct(product);

    }

    @Override
    @Transactional
    public List<Product> findAllActiveProducts() {
        return productDAO.findAllActiveProducts();
    }

    @Override
    @Transactional
    public List<Product> findAllActiveProductsByCategory(int categoryId) {
        return productDAO.findAllActiveProductsByCategory(categoryId);
    }

    @Override
    public List<Product> findAllActiveProductsByBrandOrModel(String brand, String category) {
        //TO DO
        return null;
    }

    @Override
    public List<Product> findAllActiveProductsByName(String productName) {
        //TO DO
        return null;
    }
}

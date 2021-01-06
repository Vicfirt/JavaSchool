package com.javaschool.onlineshop.dao;


import com.javaschool.onlineshop.model.entity.Product;

import java.util.List;

public interface ProductDAO {

    List<Product> findAll();

    Product getProductById(Long id);

    void addProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(Product product);

    List<Product> findAllActiveProducts();

    List<Product> findAllActiveProductsByCategory(Integer categoryId);

    List<Product> findAllActiveProductsByBrand(String brandName);

    List<Product> findAllActiveProductsByBrandOrCategory(String brandName, Integer categoryId);

    List<Product> findAllActiveProductsByName(String productName);
}

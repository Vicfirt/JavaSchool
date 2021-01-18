package com.javaschool.onlineshop.dao;


import com.javaschool.onlineshop.model.entity.Product;

import java.util.List;

public interface ProductDAO {

    List<Product> findAll();

    Product getProductById(Long id);

    Long addProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(Long id);

    List<Product> findAllActiveProducts();

    List<Product> findAllActiveProductsByCategory(Integer categoryId);

    List<Product> findAllActiveProductsByBrand(String brandName);

    List<Product> findAllActiveProductsByName(String productName);

    List<Product> findAllActiveProductsByPrice(Double minPrice, Double maxPrice);
}

package com.javaschool.onlineshop.dao;


import com.javaschool.onlineshop.model.entity.Product;

import java.util.List;

public interface ProductDAO {

    List<Product> findAllProducts();

    List<Product> findAllProductsByPrice(Double minPrice, Double maxPrice);

    Product getProductById(Long id);

    Long addProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Long id);

    List<Product> findAllActiveProducts();

    List<Product> findAllActiveProductsByCategory(Integer categoryId);

    List<Product> findAllActiveProductsByBrand(String brandName);

    List<Product> findAllActiveProductsByName(String productName);

    List<Product> findAllActiveProductsByPrice(Double minPrice, Double maxPrice);

    List<Product> findSaleProducts();
}

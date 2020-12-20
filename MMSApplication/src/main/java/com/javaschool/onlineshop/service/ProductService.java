package com.javaschool.onlineshop.service;

import com.javaschool.onlineshop.entity.Product;
import java.util.List;


public interface ProductService {

    List<Product> findAll();

    Product getProductById(int id);

    void addProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(Product product);

    List<Product> findAllActiveProducts();

    List<Product> findAllActiveProductsByCategory(int categoryId);

    List<Product> findAllActiveProductsByBrandOrModel(String brand, String category);

    List<Product> findAllActiveProductsByName(String productName);

}

package com.javaschool.onlineshop.model.dao;


import com.javaschool.onlineshop.entity.Product;
import java.util.List;

public interface ProductDAO {

    List<Product> findAll();

    Product getProductById(Long id);

    void addProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(Product product);

    List<Product> findAllActiveProducts();

    List<Product> findAllActiveProductsByCategory(int categoryId);

    List<Product> findAllActiveProductsByBrandOrModel(String brand, String category);

    List<Product> findAllActiveProductsByName(String productName);
}
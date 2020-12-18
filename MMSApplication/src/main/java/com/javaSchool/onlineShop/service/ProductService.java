package com.javaSchool.onlineShop.service;

import com.javaSchool.onlineShop.entity.Product;

import java.util.List;



public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(int productId);
}

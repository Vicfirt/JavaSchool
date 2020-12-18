package com.javaschool.onlineshop.service;

import com.javaschool.onlineshop.entity.Product;

import java.util.List;



public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(int productId);
}

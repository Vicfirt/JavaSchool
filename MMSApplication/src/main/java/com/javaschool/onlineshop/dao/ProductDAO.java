package com.javaschool.onlineshop.dao;

import com.javaschool.onlineshop.entity.Product;

import java.util.List;

public interface ProductDAO {

     List<Product> findAll();

     Product getProductById(int id);

}

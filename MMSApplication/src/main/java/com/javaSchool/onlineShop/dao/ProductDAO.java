package com.javaSchool.onlineShop.dao;

import com.javaSchool.onlineShop.entity.Product;

import java.util.List;

public interface ProductDAO {

     List<Product> findAll();

     Product getProductById(int id);




}

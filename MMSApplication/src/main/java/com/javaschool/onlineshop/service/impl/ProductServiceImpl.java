package com.javaschool.onlineshop.service.impl;

import com.javaschool.onlineshop.dao.ProductDAO;
import com.javaschool.onlineshop.entity.Product;
import com.javaschool.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    public ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Transactional
    public List<Product> getAllProducts(){
        return productDAO.findAll();
    }

    @Override
    @Transactional
    public Product getProductById(int productId) {
        return productDAO.getProductById(productId);
    }
}

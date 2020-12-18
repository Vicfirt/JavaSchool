package com.javaSchool.onlineShop.service.impl;

import com.javaSchool.onlineShop.dao.ProductDAO;
import com.javaSchool.onlineShop.entity.Product;
import com.javaSchool.onlineShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    public ProductDAO productDAO;

    @Autowired
    public void setProductDAO(ProductDAO productDAO) {
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

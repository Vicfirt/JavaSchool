package com.javaschool.onlineshop.service.impl;


import com.javaschool.onlineshop.dao.ProductDAO;
import com.javaschool.onlineshop.model.dto.ProductDTO;
import com.javaschool.onlineshop.mappers.ProductMapper;
import com.javaschool.onlineshop.model.entity.Product;
import com.javaschool.onlineshop.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductDAO productDAO, ProductMapper productMapper) {
        this.productDAO = productDAO;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public List<ProductDTO> findAll() {
        List<Product> productList = productDAO.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productList) {
            productDTOList.add(productMapper.productToProductDTO(product));
        }
        return productDTOList;
    }

    @Override
    @Transactional
    public ProductDTO getProductById(Long id) {
        Product product = productDAO.getProductById(id);
        return productMapper.productToProductDTO(product);
    }

    @Override
    @Transactional
    public void addProduct(ProductDTO product) {
        Product mappedProduct = productMapper.productDTOToProduct(product);
        productDAO.addProduct(mappedProduct);

    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        productDAO.updateProduct(product);

    }

    @Override
    @Transactional
    public void deleteProduct(Product product) {
        productDAO.deleteProduct(product);

    }

    @Override
    @Transactional
    public List<ProductDTO> findAllActiveProducts() {
        List<Product> productList = productDAO.findAllActiveProducts();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productList) {
            productDTOList.add(productMapper.productToProductDTO(product));
        }
        return productDTOList;
    }

    @Override
    @Transactional
    public List<ProductDTO> findAllActiveProductsByCategory(int categoryId) {
        List<Product> productList = productDAO.findAllActiveProductsByCategory(categoryId);
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productList) {
            productDTOList.add(productMapper.productToProductDTO(product));
        }
        return productDTOList;
    }

    @Override
    public List<Product> findAllActiveProductsByBrandOrModel(String brand, String category) {
        //TO DO
        return null;
    }

    @Override
    public List<Product> findAllActiveProductsByName(String productName) {
        //TO DO
        return null;
    }
}

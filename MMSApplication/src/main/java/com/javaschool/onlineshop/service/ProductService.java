package com.javaschool.onlineshop.service;

import com.javaschool.onlineshop.model.dto.ProductDTO;
import com.javaschool.onlineshop.model.entity.Product;

import java.util.List;


public interface ProductService {

    List<ProductDTO> findAll();

    ProductDTO getProductById(Long id);

    void addProduct(ProductDTO product);

    void updateProduct(Product product);

    void deleteProduct(Product product);

    List<ProductDTO> findAllActiveProducts();

    List<ProductDTO> findAllActiveProductsByCategory(Integer categoryId);

    List<ProductDTO> findAllActiveProductByBrand(String brandName);

    List<ProductDTO> findAllActiveProductsByBrandOrCategory(Integer categoryId, String brandName);

    List<Product> findAllActiveProductsByName(String productName);
}

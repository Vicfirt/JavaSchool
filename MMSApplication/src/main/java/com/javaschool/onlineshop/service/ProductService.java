package com.javaschool.onlineshop.service;

import com.javaschool.onlineshop.model.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> findAll();

    ProductDTO getProductById(Long id);

    void addProduct(ProductDTO product);

    void updateProduct(ProductDTO product);

    void deleteProduct(Long id);

    List<ProductDTO> findAllActiveProducts();

    List<ProductDTO> findAllActiveProductsByCategory(Integer categoryId);

    List<ProductDTO> findAllActiveProductByBrand(String brandName);

    List<ProductDTO> findAllActiveProductsByName(String productName);

    List<ProductDTO> findAllActiveProductsByPrice(Double minPrice, Double maxPrice);

    void decreaseAmount(Long productId, Integer amount);
}

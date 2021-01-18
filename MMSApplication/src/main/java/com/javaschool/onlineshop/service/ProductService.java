package com.javaschool.onlineshop.service;

import com.javaschool.onlineshop.model.dto.ProductDTO;

import java.util.List;
import java.util.Set;

public interface ProductService {

    List<ProductDTO> findAll();

    ProductDTO getProductById(Long id);

    Long addProduct(ProductDTO product);

    void updateProduct(ProductDTO product);

    void deleteProduct(Long id);

    List<ProductDTO> findAllActiveProducts();

    List<ProductDTO> findAllActiveProductsByCategory(Integer categoryId);

    List<ProductDTO> findAllActiveProductByBrand(String brandName);

    List<ProductDTO> findAllActiveProductsByName(String productName);

    List<ProductDTO> findAllActiveProductsByPrice(Double minPrice, Double maxPrice);

    void decreaseAmount(Long productId, Integer amount);

    Set<String> getBrandNames(List<ProductDTO> products);

    Set<String> getAllAvailableBrands();
}

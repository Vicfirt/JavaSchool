package com.javaschool.onlineshop.service;

import com.javaschool.onlineshop.model.dto.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface ProductService {

    List<ProductDTO> findAllProducts();

    List<ProductDTO> findAllProductsByPrice(Double minPrice, Double maxPrice);

    ProductDTO getProductById(Long id);

    void addProduct(ProductDTO productDTO, MultipartFile file);

    void updateProduct(ProductDTO productDTO, MultipartFile file);

    void deleteProduct(Long id);

    List<ProductDTO> findAllActiveProducts();

    List<ProductDTO> findAllActiveProductsByCategory(Integer categoryId);

    List<ProductDTO> findAllActiveProductByBrand(String brandName);

    List<ProductDTO> findAllActiveProductsByName(String productName);

    List<ProductDTO> findAllActiveProductsByPrice(Double minPrice, Double maxPrice);

    void decreaseAmount(Long productId, Integer amount);

    Set<String> getBrandNames(List<ProductDTO> products);

    Set<String> getAllAvailableBrands();

    List<ProductDTO> findSaleProducts();
}

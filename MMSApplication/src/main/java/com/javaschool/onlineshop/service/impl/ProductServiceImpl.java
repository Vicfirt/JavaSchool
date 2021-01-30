package com.javaschool.onlineshop.service.impl;


import com.javaschool.onlineshop.dao.ProductDAO;
import com.javaschool.onlineshop.model.dto.ProductDTO;
import com.javaschool.onlineshop.mappers.ProductMapper;
import com.javaschool.onlineshop.model.entity.Product;
import com.javaschool.onlineshop.service.ProductService;
import com.javaschool.onlineshop.utility.FileUploader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class is responsible for processing data received from product DAO as well as preparing it for sending to the UI.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductDAO productDAO, ProductMapper productMapper) {
        this.productDAO = productDAO;
        this.productMapper = productMapper;
    }

    /**
     * This method calls DAO method to get list of all products which satisfy the range.
     *
     * @param minPrice minimum price of range
     * @param maxPrice maximum price of range
     * @return if price range is present it returns filtered by price list of products. Otherwise it returns all products
     */
    @Override
    @Transactional
    public List<ProductDTO> findAllProductsByPrice(Double minPrice, Double maxPrice) {
        if (minPrice != null && maxPrice != null) {
            List<Product> productList = productDAO.findAllProductsByPrice(minPrice, maxPrice);
            List<ProductDTO> productDTOList = new ArrayList<>();
            for (Product product : productList) {
                productDTOList.add(productMapper.productToProductDTO(product));
            }
            return productDTOList;
        } else {
            return findAllProducts();
        }
    }

    /**
     * This method returns list of all products present in store. It returns even inactive products.
     *
     * @return list of products
     */
    @Override
    public List<ProductDTO> findAllProducts() {
        List<Product> productList = productDAO.findAllProducts();
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
    public void addProduct(ProductDTO productDTO, MultipartFile file) {
        Product product = productMapper.productDTOToProduct(productDTO);
        Long savedProductId = productDAO.addProduct(product);
        if (file != null) {
            FileUploader.uploadFile(file, "Product_" + savedProductId);
        }
    }

    @Override
    @Transactional
    public void updateProduct(ProductDTO productDTO, MultipartFile file) {
        if (file != null) {
            FileUploader.uploadFile(file, "Product_" + productDTO.getProductId());
        }
        productDAO.updateProduct(productMapper.productDTOToProduct(productDTO));
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        productDAO.deleteProduct(id);
    }

    /**
     * This method returns list of all active products present in store.
     *
     * @return list of active products
     */
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

    /**
     * This method is used to return active products filtered by category
     *
     * @param categoryId specifies category id
     * @return list of products
     */
    @Override
    @Transactional
    public List<ProductDTO> findAllActiveProductsByCategory(Integer categoryId) {
        List<Product> productList = productDAO.findAllActiveProductsByCategory(categoryId);
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productList) {
            productDTOList.add(productMapper.productToProductDTO(product));
        }
        return productDTOList;
    }

    /**
     * This method is used to return active products filtered by brand name.
     *
     * @param brandName requested brand name
     * @return list of products
     */
    @Override
    @Transactional
    public List<ProductDTO> findAllActiveProductByBrand(String brandName) {
        List<Product> productList = productDAO.findAllActiveProductsByBrand(brandName);
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productList) {
            productDTOList.add(productMapper.productToProductDTO(product));
        }
        return productDTOList;
    }

    /**
     * This method calls DAO method to get list of all active products which satisfy the range.
     *
     * @param minPrice minimum price of range
     * @param maxPrice maximum price of range
     * @return if price range is present it returns filtered by price list of active products.
     * Otherwise it returns all active products
     */
    @Override
    @Transactional
    public List<ProductDTO> findAllActiveProductsByPrice(Double minPrice, Double maxPrice) {
        if (minPrice != null && maxPrice != null) {
            List<Product> productList = productDAO.findAllActiveProductsByPrice(minPrice, maxPrice);
            List<ProductDTO> productDTOList = new ArrayList<>();
            for (Product product : productList) {
                productDTOList.add(productMapper.productToProductDTO(product));
            }
            return productDTOList;
        } else {
            return findAllActiveProducts();
        }
    }

    /**
     * This method is used to return filtered list of active products in accordance with the requested product name
     *
     * @param productName name to filter by
     * @return list of products
     */
    @Override
    @Transactional
    public List<ProductDTO> findAllActiveProductsByName(String productName) {
        List<Product> productList = productDAO.findAllActiveProductsByName(productName);
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productList) {
            productDTOList.add(productMapper.productToProductDTO(product));
        }
        return productDTOList;
    }

    /**
     * This method decreases amount of specified product in stock.
     *
     * @param productId specifies product to decrease amount
     * @param amount    amount to be decreased by
     */
    @Override
    @Transactional
    public void decreaseAmount(Long productId, Integer amount) {
        Product product = productDAO.getProductById(productId);
        Integer newAmount = product.getAmountInStock() - amount;
        product.setAmountInStock(newAmount);
        if (newAmount == 0) product.setActive(false);
        productDAO.updateProduct(product);
    }

    /**
     * This method is used to return sorted set of brand names.
     *
     * @param products products whose brand names will be used
     * @return names of the product brands
     */
    @Override
    public Set<String> getBrandNames(List<ProductDTO> products) {
        Set<String> brandNames = new TreeSet<>();
        for (ProductDTO product : products) {
            brandNames.add(product.getProductBrand());
        }
        return brandNames;
    }

    /**
     * This method returns all available brand names
     *
     * @return brand names of available products
     */
    @Override
    @Transactional
    public Set<String> getAllAvailableBrands() {
        List<ProductDTO> productDTOList = findAllActiveProducts();
        return getBrandNames(productDTOList);
    }

    /**
     * This method returns products to provide home page advertisement
     *
     * @return products list
     */
    @Override
    @Transactional
    public List<ProductDTO> findSaleProducts() {
        List<Product> productList = productDAO.findSaleProducts();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productList) {
            productDTOList.add(productMapper.productToProductDTO(product));
        }
        return productDTOList;
    }
}

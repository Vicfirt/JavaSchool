package com.javaschool.onlineshop.model.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class ProductDTO {

    private Long productId;

    @Size(min = 2, max = 40, message = "Product name must contain 2 to 40 characters.")
    private String productName;

    @Min(value = 1, message = "The price cannot be less than 1")
    @Max(value = 1000000, message = "The price should not exceed 1000000")
    private Double productPrice;

    private Integer categoryId;

    @Size(min = 2, max = 25, message = "Brand name must contain 2 to 25 characters.")
    private String productBrand;

    @Size( max = 30, message = "Model name must contain 2 to 30 characters.")
    private String productModel;

    @Min(value = 1, message = "Weight cannot be less than 1")
    @Max(value = 100000, message = "Weight should not exceed 1000000" )
    private Integer productWeight;

    @Min(value = 0, message = "Capacity cannot be less than 0")
    @Max(value = 2000, message = "Capacity should not exceed 2000")
    private Integer productCapacity;

    @Min(value = 1, message = "Amount in stock cannot be less than 1")
    @Max(value = 10000, message = "Amount in stock should not exceed 1000000")
    private Integer amountInStock;

    @Size( max = 255, message = "The number of characters in the description cannot be more than 255")
    private String productDescription;

    private Boolean isActive;

    @Size( max = 255, message = "The number of characters in the product image URL cannot be more than 255")
    private String productImage;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public Integer getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(Integer productWeight) {
        this.productWeight = productWeight;
    }

    public Integer getProductCapacity() {
        return productCapacity;
    }

    public void setProductCapacity(Integer productCapacity) {
        this.productCapacity = productCapacity;
    }

    public Integer getAmountInStock() {
        return amountInStock;
    }

    public void setAmountInStock(Integer amountInStock) {
        this.amountInStock = amountInStock;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}

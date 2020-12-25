package com.javaschool.onlineshop.dto;


public class ProductDTO {

    private Long productId;

    private String productName;

    private Double productPrice;

    private Integer categoryId;

    private String productBrand;

    private String productModel;

    private String productWeight;

    private String productCapacity;

    private String amountInStock;

    private Boolean isActive;

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

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }

    public String getProductCapacity() {
        return productCapacity;
    }

    public void setProductCapacity(String productCapacity) {
        this.productCapacity = productCapacity;
    }

    public String getAmountInStock() {
        return amountInStock;
    }

    public void setAmountInStock(String amountInStock) {
        this.amountInStock = amountInStock;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}

package com.javaschool.onlineshop.entity;


import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Column;


@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;


    @Column(name = "name")
    private String productName;

    @Column(name = "price")
    private long productPrice;

    @Column(name = "category_id")
    private int categoryId;


    @Column(name = "brand")
    private String productBrand;

    @Column(name = "model")
    private String productModel;

    @Column(name = "weight")
    private String productWeight;


    @Column(name = "capacity")
    private String productCapacity;

    @Column(name = "amount_in_stock")
    private String amountInStock;

    @Column(name = "is_active")
    private boolean isActive;


    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(long productPrice) {
        this.productPrice = productPrice;
    }

    public int getCategotrId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
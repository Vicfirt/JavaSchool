package com.javaschool.onlineshop.entity;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "name")
    private String productName;

    @Column(name = "price")
    private long productPrice;


    @Column(name = "category")
    private String productCategory;


    @Column(name = "parameters")
    private String productParameters;


    @Column(name = "weight")
    private String productWeight;


    @Column(name = "capacity")
    private String productCaCapacity;


    @Column(name = "amount_in_stock")
    private String amountInStock;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductParameters() {
        return productParameters;
    }

    public void setProductParameters(String productParameters) {
        this.productParameters = productParameters;
    }

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }

    public String getProductCaCapacity() {
        return productCaCapacity;
    }

    public void setProductCaCapacity(String productCaCapacity) {
        this.productCaCapacity = productCaCapacity;
    }

    public String getAmountInStock() {
        return amountInStock;
    }

    public void setAmountInStock(String amountInStock) {
        this.amountInStock = amountInStock;
    }
}

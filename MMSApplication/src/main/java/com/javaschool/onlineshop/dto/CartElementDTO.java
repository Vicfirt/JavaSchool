package com.javaschool.onlineshop.dto;


import com.javaschool.onlineshop.entity.Product;

public class CartElementDTO {

    private Long id;

    private Long cartId;

    private Product product;

    private Integer productCount;

    private Double elementPrice;

    private Boolean isAvailable = true;

    private Double totalPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Double getElementPrice() {
        return elementPrice;
    }

    public void setElementPrice(Double elementPrice) {
        this.elementPrice = elementPrice;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}


package com.javaschool.onlineshop.dto;


public class CartDTO {

    private Long cartId;

    private CustomerDTO customer;

    private Double cartTotal;

    private Integer elementsInCart;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public Double getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(Double cartTotal) {
        this.cartTotal = cartTotal;
    }

    public Integer getElementsInCart() {
        return elementsInCart;
    }

    public void setElementsInCart(Integer elementsInCart) {
        this.elementsInCart = elementsInCart;
    }
}

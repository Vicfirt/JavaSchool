package com.javaschool.onlineshop.model.entity;


import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @Column(name = "cart_total")
    private Double cartTotal;

    @Column(name = "elements_in_cart")
    private Integer elementsInCart;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
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
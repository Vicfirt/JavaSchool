package com.javaschool.onlineshop.entity;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;

import java.util.List;


@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartId;

    @OneToOne
    private Customer customer;

    @Column(name = "total_price")
    private double totalPrice;
    @Column(name = "elements_in_cart")
    private int elementsInCart;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CartElement> cartElements;

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getElementsInCart() {
        return elementsInCart;
    }

    public void setElementsInCart(int elementsInCart) {
        this.elementsInCart = elementsInCart;
    }

    public List<CartElement> getCartElements() {
        return cartElements;
    }

    public void setCartElements(List<CartElement> cartElements) {
        this.cartElements = cartElements;
    }
}

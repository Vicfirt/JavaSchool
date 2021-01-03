package com.javaschool.onlineshop.dao;


import com.javaschool.onlineshop.model.entity.Cart;

public interface CartDAO {

    Cart getCart(Long customerId);

    boolean updateCart(Cart cart);

    boolean addCart(Cart cart);

    public Cart getCartById(Long cartId);
}
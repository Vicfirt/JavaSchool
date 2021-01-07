package com.javaschool.onlineshop.dao;


import com.javaschool.onlineshop.model.entity.Cart;

public interface CartDAO {

    void updateCart(Cart cart);

    void addCart(Cart cart);
}

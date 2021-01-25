package com.javaschool.onlineshop.dao;


import com.javaschool.onlineshop.model.entity.Cart;

public interface CartDAO {

    Cart updateCart(Cart cart);

    Cart addCart(Cart cart);
}

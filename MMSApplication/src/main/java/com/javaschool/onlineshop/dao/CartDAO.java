package com.javaschool.onlineshop.dao;

import com.javaschool.onlineshop.entity.Cart;
import com.javaschool.onlineshop.entity.CartElement;

import java.util.List;

public interface CartDAO {

    Cart getCart(Long customerId);

    boolean updateCart(Cart cart);

    boolean addCart(Cart cart);

    public Cart getCartById(Long cartId);
}

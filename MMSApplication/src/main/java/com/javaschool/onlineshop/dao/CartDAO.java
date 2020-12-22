package com.javaschool.onlineshop.dao;

import com.javaschool.onlineshop.entity.Cart;
import com.javaschool.onlineshop.entity.CartElement;

import java.util.List;

public interface CartDAO {

    CartElement getCartElement(int id);

    boolean add(CartElement cartElement);

    boolean update(CartElement cartElement);

    boolean delete(CartElement cartElement);

    List<CartElement> cartList();

    boolean updateCart(Cart cart);
}

package com.javaschool.onlineshop.service;

import com.javaschool.onlineshop.dto.CartElementDTO;
import com.javaschool.onlineshop.entity.Cart;
import com.javaschool.onlineshop.entity.CartElement;

import java.util.List;


public interface CartService {

    CartElementDTO getCartElement(int id);

    boolean add(CartElement cartElement);

    boolean update(CartElement cartElement);

    boolean delete(CartElement cartElement);

    List<CartElementDTO> cartList();

    boolean updateCart(Cart cart);
}

package com.javaschool.onlineshop.service;

import com.javaschool.onlineshop.dto.CartElementDTO;
import com.javaschool.onlineshop.entity.Cart;
import com.javaschool.onlineshop.entity.CartElement;
import com.javaschool.onlineshop.entity.Product;

import java.util.List;

public interface CartService {

    Cart getCart();

    CartElementDTO getCartElement(int id);

    boolean addCartElement(Product product);

    boolean update(CartElement cartElement);

    boolean delete(CartElement cartElement);

    List<CartElementDTO> cartList();

    boolean updateCart(Cart cart);
}

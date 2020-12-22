package com.javaschool.onlineshop.service;

import com.javaschool.onlineshop.entity.Cart;
import com.javaschool.onlineshop.entity.CartElement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

    CartElement getCartElement(int id);

    boolean add(CartElement cartElement);

    boolean update(CartElement cartElement);

    boolean delete(CartElement cartElement);

    public List<CartElement> cartList();

    boolean updateCart(Cart cart);
}

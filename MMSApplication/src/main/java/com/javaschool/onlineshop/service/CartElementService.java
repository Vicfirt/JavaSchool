package com.javaschool.onlineshop.service;

import com.javaschool.onlineshop.entity.Cart;
import com.javaschool.onlineshop.entity.CartElement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartElementService {

    CartElement getCart(int id);

    boolean add(CartElement cartElement);

    boolean update(CartElement cartElement);

    boolean delete(CartElement cartElement);

    public List<CartElement> cartList(int cartId);

    boolean updateCart(Cart cart);

}

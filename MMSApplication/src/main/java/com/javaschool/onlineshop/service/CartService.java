package com.javaschool.onlineshop.service;


import com.javaschool.onlineshop.dto.CartElementDTO;
import com.javaschool.onlineshop.entity.Cart;
import com.javaschool.onlineshop.entity.CartElement;
import com.javaschool.onlineshop.entity.Product;

import java.util.List;

public interface CartService {

    Cart getCart();

    void addCartElement(Product product);

    void update(CartElement cartElement);

    void delete(CartElement cartElement);

    List<CartElementDTO> getCartElements();

    Double countTotal();

}

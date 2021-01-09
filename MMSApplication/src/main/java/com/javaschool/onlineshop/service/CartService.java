package com.javaschool.onlineshop.service;


import com.javaschool.onlineshop.model.dto.CartElementDTO;
import com.javaschool.onlineshop.model.dto.ProductDTO;
import com.javaschool.onlineshop.model.entity.Cart;

import java.util.List;

public interface CartService {

    Cart getCart();

    void addCartElement(ProductDTO productDTO);

    void updateCart(Cart cart);

    void delete(Long cartElementId);

    List<CartElementDTO> getCartElements();

    Double countTotal();

    void updateCartElement(Long cartElementId, Integer quantity);
}

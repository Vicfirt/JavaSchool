package com.javaschool.onlineshop.service;


import com.javaschool.onlineshop.model.dto.CartElementDTO;
import com.javaschool.onlineshop.model.dto.ProductDTO;
import com.javaschool.onlineshop.model.entity.Cart;
import com.javaschool.onlineshop.model.entity.CartElement;

import java.util.List;

public interface CartService {

    Cart getCart();

    void addCartElement(ProductDTO productDTO);

    void update(CartElement cartElement);

    void delete(Long cartElementId);

    List<CartElementDTO> getCartElements();

    Double countTotal();

    void updateCartElement(Long cartElementId, Integer quantity);
}

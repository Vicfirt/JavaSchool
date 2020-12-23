package com.javaschool.onlineshop.controllers;


import com.javaschool.onlineshop.dto.CartElementDTO;
import com.javaschool.onlineshop.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    public List<CartElementDTO> getAllItemsInCart() {
        return cartService.cartList();
    }
}

package com.javaschool.onlineshop.controllers;


import com.javaschool.onlineshop.dto.CartElementDTO;
import com.javaschool.onlineshop.entity.Product;
import com.javaschool.onlineshop.service.CartService;
import com.javaschool.onlineshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    private final ProductService productService;

    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping
    public String getAllItemsInCart(Model model) {
        List<CartElementDTO> cartElementDTOList = cartService.getCartElements();
        model.addAttribute("cartItems", cartElementDTOList);
        return "cart";
    }

    @RequestMapping("add/product/{id}")
    public String addCartElement(@PathVariable("id") long id) {
        Product product = productService.getProductById(id);
        cartService.addCartElement(product);
        return "redirect:/catalog";
    }
}

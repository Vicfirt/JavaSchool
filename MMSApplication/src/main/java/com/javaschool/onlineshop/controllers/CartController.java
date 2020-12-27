package com.javaschool.onlineshop.controllers;


import com.javaschool.onlineshop.dto.CartElementDTO;
import com.javaschool.onlineshop.dto.ProductDTO;
import com.javaschool.onlineshop.service.CartService;
import com.javaschool.onlineshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("counter", cartService.getCart().getElementsInCart());
        model.addAttribute("total", cartService.getCart().getCartTotal());
        return "cart";
    }

    @GetMapping("add/product/{id}")
    public String addCartElement(@PathVariable("id") Long id) {
        ProductDTO product = productService.getProductById(id);
        cartService.addCartElement(product);
        return "redirect:/catalog";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam("element_Id") Long id) {
        cartService.delete(id);
        return "redirect:/cart";
    }

    @GetMapping("/change")
    public String plus(@RequestParam("element_Id") Long id, @RequestParam("quantity") Integer quantity) {
        cartService.updateCartElement(id, quantity);
        return "redirect:/cart";
    }
}

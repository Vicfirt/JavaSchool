package com.javaschool.onlineshop.controllers;


import com.javaschool.onlineshop.dto.ProductDTO;
import com.javaschool.onlineshop.service.CartService;
import com.javaschool.onlineshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * This class implements the logic for transitions to subpages within the home page.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    private final ProductService productService;

    private final CartService cartService;

    public HomeController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    /**
     * This method will display a catalog of all products on the home page.
     */
    @GetMapping
    public String homePage(Model model) {
        model.addAttribute("counter", cartService.getCart().getElementsInCart());
        return "home_page";
    }

    @GetMapping("/catalog")
    public String catalog(Model model) {
        List<ProductDTO> allProducts = productService.findAll();
        model.addAttribute("products", allProducts);
        model.addAttribute("counter", cartService.getCart().getElementsInCart());
        return "catalog";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("counter", cartService.getCart().getElementsInCart());
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("counter", cartService.getCart().getElementsInCart());
        return "/signup";
    }
}

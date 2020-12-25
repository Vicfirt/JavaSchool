package com.javaschool.onlineshop.controllers;

import com.javaschool.onlineshop.service.ProductService;
import com.javaschool.onlineshop.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * This class implements the logic for transitions to subpages within the home page.
 */
@Controller
@RequestMapping
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * This method will display a catalog of all products on the home page.
     */
    @GetMapping
    public String homePage(Model model) {
        return "home_page";
    }

    @GetMapping("/catalog")
    public String catalog(Model model) {
        List<Product> allProducts = productService.findAll();
        model.addAttribute("products", allProducts);
        return "catalog";
    }
}

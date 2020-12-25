package com.javaschool.onlineshop.controllers;


import com.javaschool.onlineshop.entity.Product;
import com.javaschool.onlineshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This class implements the logic of transition to a specific page
 * * associated with products in accordance with the received URL.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * The method moves to a page with detailed information about a specific product.
     * Product according to the specified ID will also be sent for presentation for further use.
     */
    @GetMapping("/{productId}")
    public String getProductInfoPage(@PathVariable("productId") Long productId, Model model) {
        Product product = productService.getProductById(productId);
        model.addAttribute("product", product);
        return "product_info";
    }
}



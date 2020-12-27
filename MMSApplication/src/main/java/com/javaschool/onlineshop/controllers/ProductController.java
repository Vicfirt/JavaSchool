package com.javaschool.onlineshop.controllers;


import com.javaschool.onlineshop.dto.ProductDTO;
import com.javaschool.onlineshop.service.CartService;
import com.javaschool.onlineshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * This class implements the logic of transition to a specific page
 * * associated with products in accordance with the received URL.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    private final CartService cartService;

    public ProductController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    /**
     * The method moves to a page with detailed information about a specific product.
     * Product according to the specified ID will also be sent for presentation for further use.
     */
    @GetMapping("/{productId}")
    public String getProductInfoPage(@PathVariable("productId") Long productId, Model model) {
        ProductDTO product = productService.getProductById(productId);
        model.addAttribute("product", product);
        model.addAttribute("counter", cartService.getCart().getElementsInCart());
        return "product_info";
    }

    @PostMapping("/new")
    public String create(
            @RequestParam("categoryId") Integer categoryId,
            @RequestParam("status") Boolean productStatus,
            @Valid @ModelAttribute("product") ProductDTO product,
            RedirectAttributes redirectAttributes,
            BindingResult bindingResult, Model model) {
        ProductDTO productIdExists = productService.getProductById(product.getProductId());
        if (productIdExists != null) {
            bindingResult.rejectValue("productId", "Provided code is already in use");
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("product", product);
            return "product_createform";
        }
        product.setActive(productStatus);
        product.setCategoryId(categoryId);
        productService.addProduct(product);
        return "redirect:/catalog";
    }

    @GetMapping("/new")
    public String createForm(ProductDTO product, Model model) {
        model.addAttribute("product", product);
        model.addAttribute("counter", cartService.getCart().getElementsInCart());
        return "product_createform";
    }
}



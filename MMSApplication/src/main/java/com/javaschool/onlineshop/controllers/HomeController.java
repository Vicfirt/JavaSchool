package com.javaschool.onlineshop.controllers;


import com.javaschool.onlineshop.model.dao.CustomerDAO;
import com.javaschool.onlineshop.model.dto.CustomerDTO;
import com.javaschool.onlineshop.model.dto.ProductDTO;
import com.javaschool.onlineshop.service.CartService;
import com.javaschool.onlineshop.service.CustomerService;
import com.javaschool.onlineshop.service.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * This class implements the logic for transitions to subpages within the home page.
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    private final ProductService productService;

    private final CartService cartService;

    private final CustomerService customerService;

    private final CustomerDAO customerDAO;

    public HomeController(ProductService productService, CartService cartService, CustomerService customerService, CustomerDAO customerDAO) {
        this.productService = productService;
        this.cartService = cartService;
        this.customerService = customerService;
        this.customerDAO = customerDAO;
    }

    /**
     * This method will display a catalog of all products on the home page.
     */
    @GetMapping
    public String homePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null)
            return "home_page";

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("CUSTOMER"))) {
            CustomerDTO customer = customerService.getCustomer();
            model.addAttribute("customer", customer);
        }
        return "home_page";
    }

    @GetMapping("/catalog")
    public String catalog(Model model, Authentication authentication) {
        List<ProductDTO> allProducts = productService.findAll();
        model.addAttribute("products", allProducts);
        if (authentication == null)
            return "catalog";

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("CUSTOMER"))) {
            model.addAttribute("cart", cartService.getCart());
        }
        return "catalog";
    }
}

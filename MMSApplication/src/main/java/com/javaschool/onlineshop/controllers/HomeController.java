package com.javaschool.onlineshop.controllers;


import com.javaschool.onlineshop.model.dto.CustomerDTO;
import com.javaschool.onlineshop.model.dto.ProductDTO;
import com.javaschool.onlineshop.service.CustomerService;
import com.javaschool.onlineshop.service.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * This class implements the logic for transitions to subpages within the home page.
 */
@Controller
@RequestMapping
public class HomeController {

    private final ProductService productService;

    private final CustomerService customerService;

    public HomeController(ProductService productService, CustomerService customerService) {
        this.productService = productService;
        this.customerService = customerService;
    }

    /**
     * This method will display a catalog of all products on the home page.
     */
    @GetMapping("/home")
    public String homePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null)
            return "home_page";

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("CUSTOMER"))) {
            CustomerDTO customer = customerService.getByUsername(authentication.getName());
            model.addAttribute("customer", customer);
        }
        return "home_page";
    }

    @GetMapping("/catalog")
    public String catalog(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<ProductDTO> allProducts = productService.findAll();
        model.addAttribute("products", allProducts);
        if (authentication == null)
            return "catalog";

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("CUSTOMER"))) {
            CustomerDTO customer = customerService.getByUsername(authentication.getName());
            model.addAttribute("customer", customer);
        }
        return "catalog";
    }

    @GetMapping(value = {"/catalog/{categoryId}", "/catalog/{brandName}"})
    public String catalogByCategory(@PathVariable(value = "categoryId", required = false) Integer categoryId,
                                    @PathVariable(value = "brandName", required = false) String brandName,
                                    Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("CUSTOMER"))) {
            CustomerDTO customer = customerService.getCustomer();
            model.addAttribute("customer", customer);
        }
        if (categoryId != null && brandName != null) {
            List<ProductDTO> productDTOList = productService.findAllActiveProductsByBrandOrCategory(categoryId, brandName);
            model.addAttribute("filtredProducts", productDTOList);
            return "catalog";
        }

        if (categoryId != null) {
            List<ProductDTO> productDTOList = productService.findAllActiveProductsByCategory(categoryId);
            model.addAttribute("filtredProducts", productDTOList);
            return "catalog";
        }

        if (brandName != null) {
            List<ProductDTO> productDTOList = productService.findAllActiveProductByBrand(brandName);
            model.addAttribute("filtredProducts", productDTOList);
            return "catalog";
        }
        return "catalog";
    }
}

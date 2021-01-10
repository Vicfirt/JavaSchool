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
import org.springframework.web.bind.annotation.RequestParam;

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
    @GetMapping
    public String homePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null)
            return "home_page";

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("CUSTOMER"))
                || authentication.getAuthorities().contains(new SimpleGrantedAuthority("EMPLOYEE"))) {
            CustomerDTO customer = customerService.getByUsername(authentication.getName());
            model.addAttribute("customer", customer);
        }
        return "home_page";
    }

    @GetMapping("/catalog")
    public String catalog(Model model,
                          @RequestParam(name = "minValue", required = false) Double minPrice,
                          @RequestParam(name = "maxValue", required = false) Double maxPrice) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<ProductDTO> allProducts = productService.findAllActiveProductsByPrice(minPrice, maxPrice);
        model.addAttribute("products", allProducts);
        if (authentication == null)
            return "catalog";

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("CUSTOMER"))
                || authentication.getAuthorities().contains(new SimpleGrantedAuthority("EMPLOYEE"))) {
            CustomerDTO customer = customerService.getByUsername(authentication.getName());
            model.addAttribute("customer", customer);
        }
        return "catalog";
    }

    @GetMapping(value = {"/catalog/category/{categoryId}"})
    public String catalogByCategory(@PathVariable(value = "categoryId", required = false) Integer categoryId,
                                    Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("CUSTOMER"))
                || authentication.getAuthorities().contains(new SimpleGrantedAuthority("EMPLOYEE"))) {
            CustomerDTO customer = customerService.getCustomer();
            model.addAttribute("customer", customer);
        }
        List<ProductDTO> productDTOList = productService.findAllActiveProductsByCategory(categoryId);
        model.addAttribute("filteredProducts", productDTOList);

        return "catalog";
    }

    @GetMapping("catalog/brand")
    public String catalogByBrand(Model model, @RequestParam(name = "brandName") String brandName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("CUSTOMER"))
                || authentication.getAuthorities().contains(new SimpleGrantedAuthority("EMPLOYEE"))) {
            CustomerDTO customer = customerService.getCustomer();
            model.addAttribute("customer", customer);
        }
        List<ProductDTO> productDTOList = productService.findAllActiveProductByBrand(brandName);
        model.addAttribute("filteredProducts", productDTOList);
        return "catalog";
    }

    @GetMapping("catalog/name")
    public String catalogByName(Model model, @RequestParam(name = "productName") String productName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("CUSTOMER"))
                || authentication.getAuthorities().contains(new SimpleGrantedAuthority("EMPLOYEE"))) {
            CustomerDTO customer = customerService.getCustomer();
            model.addAttribute("customer", customer);
        }
        List<ProductDTO> productDTOList = productService.findAllActiveProductsByName(productName);
        model.addAttribute("filteredProducts", productDTOList);
        return "catalog";
    }
}

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
import java.util.Set;

/**
 * This class is responsible for displaying homepage and catalog content.
 */
@Controller
@RequestMapping
public class ContentController {

    private final ProductService productService;

    private final CustomerService customerService;

    public ContentController(ProductService productService, CustomerService customerService) {
        this.productService = productService;
        this.customerService = customerService;
    }

    /**
     * The method is used to return the start page.
     * @param model                will be sent to the view
     * @return homepage view
     */
    @GetMapping
    public String homePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<ProductDTO> products = productService.findSaleProducts();
        model.addAttribute("products", products);
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("CUSTOMER"))
                || authentication.getAuthorities().contains(new SimpleGrantedAuthority("EMPLOYEE"))) {
            CustomerDTO customer = customerService.getByUsername(authentication.getName());
            model.addAttribute("customer", customer);
        }
        return "home_page";
    }

    /**
     * The method is used to return the catalog. If parameters are supplied it returns the catalog filtered by price.
     *
     * @param model                 will be sent to the view
     * @param minPrice              minimum price for filtration
     * @param maxPrice              minimum price for filtration
     * @return catalog view
     */
    @GetMapping("/catalog")
    public String catalog(Model model,
                          @RequestParam(name = "minValue", required = false) Double minPrice,
                          @RequestParam(name = "maxValue", required = false) Double maxPrice) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<ProductDTO> allProducts;
        Set<String> brands = productService.getAllAvailableBrands();
        model.addAttribute("brands", brands);
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("CUSTOMER"))) {
            CustomerDTO customerDTO = customerService.getByUsername(authentication.getName());
            allProducts = productService.findAllActiveProductsByPrice(minPrice, maxPrice);
            model.addAttribute("customer", customerDTO);
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("EMPLOYEE"))) {
            CustomerDTO customerDTO = customerService.getByUsername(authentication.getName());
            allProducts = productService.findAllProductsByPrice(minPrice, maxPrice);
            model.addAttribute("customer", customerDTO);
        } else {
            allProducts = productService.findAllActiveProductsByPrice(minPrice, maxPrice);
        }
        model.addAttribute("products", allProducts);
        return "catalog";
    }

    /**
     * The method is used to return the catalog filtered by category.
     *
     * @param categoryId            id of the category to filter
     * @param model                 will be sent to the view
     * @return catalog view
     */
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
        Set<String> brands = productService.getBrandNames(productDTOList);
        model.addAttribute("filteredProducts", productDTOList);
        model.addAttribute("brands", brands);
        return "catalog";
    }

    /**
     * The method is used to return the catalog filtered by brand.
     * @param model                 will be sent to the view
     * @param brandName             name of the brand to filter by
     * @return catalog view
     */
    @GetMapping("catalog/brand/{brandName}")
    public String catalogByBrand(Model model, @PathVariable(value = "brandName") String brandName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("CUSTOMER"))
                || authentication.getAuthorities().contains(new SimpleGrantedAuthority("EMPLOYEE"))) {
            CustomerDTO customer = customerService.getCustomer();
            model.addAttribute("customer", customer);
        }
        List<ProductDTO> productDTOList = productService.findAllActiveProductByBrand(brandName);
        Set<String> brands = productService.getAllAvailableBrands();
        model.addAttribute("filteredProducts", productDTOList);
        model.addAttribute("brands", brands);
        return "catalog";
    }

    /**
     * The method is used to filter catalog by product name and return it.
     * @param model                 will be sent to the view
     * @param productName           name of the product to filter by
     * @return catalog view
     */
    @GetMapping("catalog/name")
    public String catalogByName(Model model, @RequestParam(name = "productName") String productName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("CUSTOMER"))
                || authentication.getAuthorities().contains(new SimpleGrantedAuthority("EMPLOYEE"))) {
            CustomerDTO customer = customerService.getCustomer();
            model.addAttribute("customer", customer);
        }
        List<ProductDTO> productDTOList = productService.findAllActiveProductsByName(productName);
        Set<String> brands = productService.getAllAvailableBrands();
        model.addAttribute("filteredProducts", productDTOList);
        model.addAttribute("brands", brands);
        return "catalog";
    }
}

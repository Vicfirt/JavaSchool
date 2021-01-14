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

    private final CustomerService customerService;

    public ProductController(ProductService productService, CustomerService customerService) {
        this.productService = productService;
        this.customerService = customerService;
    }

    /**
     * The method moves to a page with detailed information about a specific product.
     * Product according to the specified ID will also be sent for presentation for further use.
     */
    @GetMapping("/{productId}")
    public String getProductInfoPage(@PathVariable("productId") Long productId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ProductDTO product = productService.getProductById(productId);
        model.addAttribute("product", product);

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("CUSTOMER"))) {
            CustomerDTO customer = customerService.getByUsername(authentication.getName());
            model.addAttribute("customer", customer);
        }
        return "product_info";
    }

    @PostMapping("/employee/new")
    public String createProduct(
            @RequestParam("categoryId") Integer categoryId,
            @RequestParam("status") Boolean productStatus,
            @Valid @ModelAttribute("product") ProductDTO product,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("product", product);
            return "product_createform";
        }
        product.setActive(productStatus);
        product.setCategoryId(categoryId);
        productService.addProduct(product);
        return "redirect:/catalog";
    }

    @GetMapping("/employee/new")
    public String createForm(ProductDTO product, Model model) {
        model.addAttribute("product", product);
        model.addAttribute("customer", customerService.getCustomer());
        return "product_createform";
    }

    @GetMapping("/employee/edition/{id}")
    public String getProductEditForm(@PathVariable("id") Long productId, Model model) {
        ProductDTO product = productService.getProductById(productId);
        model.addAttribute("product", product);
        return "product_editform";
    }

    @PostMapping("/employee/edition/{id}")
    public String editProduct(@PathVariable("id") Long productId,
                              @RequestParam("categoryId") Integer categoryId,
                              @RequestParam("status") Boolean productStatus,
                              @Valid @ModelAttribute("product") ProductDTO product,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("product", product);
            return "product/employee/edition" + productId;
        }
        product.setCategoryId(categoryId);
        product.setActive(productStatus);
        productService.updateProduct(product);
        return "redirect:/catalog";
    }

    @GetMapping("/employee/deletion/{id}")
    public String deleteProduct(@PathVariable("id") Long productId) {
        productService.deleteProduct(productId);
        return "redirect:/catalog";
    }
}



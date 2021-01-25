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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * This class is responsible for the actions with products.
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
     */
    @GetMapping("/{productId}")
    public String getProductInfoPage(@PathVariable("productId") Long productId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ProductDTO product = productService.getProductById(productId);
        model.addAttribute("product", product);


        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("CUSTOMER"))
                || authentication.getAuthorities().contains(new SimpleGrantedAuthority("EMPLOYEE"))) {
            CustomerDTO customer = customerService.getByUsername(authentication.getName());
            model.addAttribute("customer", customer);
        }
        return "product_info";
    }

    /**
     * This method is responsible for creating a new product.
     * @param file                     image file obtained from the form
     * @param productStatus            status to be set to the product
     * @param product                  product to be created
     * @param bindingResult            used for form validation
     * @param redirectAttributes       these attributes will be returned to the previous form in case of validation error
     * @return
     */
    @PostMapping(value = "/employee/new", headers = "content-type=multipart/*")
    public String createProduct(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("status") Boolean productStatus,
            @Valid @ModelAttribute("product") ProductDTO product,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("product", product);
            return "product_createform";
        }
        product.setActive(productStatus);
        productService.addProduct(product, file);
        return "redirect:/catalog";
    }

    /**
     * This method returns form to create product.
     * @param product               creating product
     * @param model                 this will be sent to the view
     * @return  product create form view
     */
    @GetMapping("/employee/new")
    public String createForm(ProductDTO product, Model model) {
        model.addAttribute("product", product);
        model.addAttribute("customer", customerService.getCustomer());
        return "product_createform";
    }

    /**
     * This method returns form to edit product information.
     * @param productId             specifies product to be created
     * @param model                 this will be sent to the view
     * @return product edit form view
     */
    @GetMapping("/employee/edition/{id}")
    public String getProductEditForm(@PathVariable("id") Long productId, Model model) {
        ProductDTO product = productService.getProductById(productId);
        model.addAttribute("product", product);
        return "product_editform";
    }

    /**
     * This method is responsible for editing product information.
     * @param productId              specifies product to be edited
     * @param file                   image file obtained from the form
     * @param productStatus          status to be set to the product
     * @param product                product to be edited
     * @param bindingResult          used for form validation
     * @param redirectAttributes     these attributes will be returned to the previous form in case of validation error
     * @return redirect to catalog view
     */
    @PostMapping(value = "/employee/edition/{id}", headers = "content-type=multipart/*")
    public String editProduct(@PathVariable("id") Long productId,
                              @RequestParam(value = "file", required = false) MultipartFile file,
                              @RequestParam("status") Boolean productStatus,
                              @Valid @ModelAttribute("product") ProductDTO product,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("product", product);
            return "product/employee/edition/" + productId;
        }
        product.setActive(productStatus);
        productService.updateProduct(product, file);
        return "redirect:/catalog";
    }

    /**
     * This method is responsible for deleting the specified product.
     * @param productId             specifies product to be deleted
     * @return  redirect to catalog view
     */
    @GetMapping("/employee/deletion/{id}")
    public String deleteProduct(@PathVariable("id") Long productId) {
        productService.deleteProduct(productId);
        return "redirect:/catalog";
    }
}



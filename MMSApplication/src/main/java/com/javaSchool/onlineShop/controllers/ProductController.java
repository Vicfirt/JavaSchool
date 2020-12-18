package com.javaSchool.onlineShop.controllers;


import com.javaSchool.onlineShop.entity.Product;
import com.javaSchool.onlineShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * This class implements the logic of transition to a specific page
 *  * associated with products in accordance with the received URL.
 */

@Controller
@RequestMapping("/product")
public class ProductController {

    public ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    /**
     * The method moves to a page with detailed information about a specific product.
     * Product according to the specified ID will also be sent for presentation for further use.
     */

    @GetMapping("/{productId}")
    public String getProductInfoPage(@PathVariable("productId") int productId, Model model){

        Product product = productService.getProductById(productId);

        model.addAttribute("product", product);

        return "product_info";
    }
}



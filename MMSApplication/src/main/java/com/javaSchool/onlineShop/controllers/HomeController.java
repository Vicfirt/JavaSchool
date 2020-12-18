package com.javaSchool.onlineShop.controllers;


import com.javaSchool.onlineShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This class implements the logic for transitions to subpages within the home page.
 */
@Controller
public class HomeController {

    ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    /**
     *This method will display a catalog of all products on the home page.
     */

    @GetMapping("/")
    public String homePage(Model model){

        model.addAttribute("products", productService.getAllProducts());

        return "home_page";
    }






}

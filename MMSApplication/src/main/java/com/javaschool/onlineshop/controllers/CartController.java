package com.javaschool.onlineshop.controllers;


import com.javaschool.onlineshop.model.dto.CartElementDTO;
import com.javaschool.onlineshop.model.dto.CustomerDTO;
import com.javaschool.onlineshop.model.dto.OrderInfoDTO;
import com.javaschool.onlineshop.model.dto.ProductDTO;
import com.javaschool.onlineshop.service.CartService;
import com.javaschool.onlineshop.service.CustomerService;
import com.javaschool.onlineshop.service.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    private final ProductService productService;

    private final CustomerService customerService;

    public CartController(CartService cartService, ProductService productService, CustomerService customerService) {
        this.cartService = cartService;
        this.productService = productService;
        this.customerService = customerService;
    }

    @GetMapping
    public String getAllItemsInCart(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomerDTO customer = customerService.getByUsername(authentication.getName());
        List<CartElementDTO> cartElementDTOList = cartService.getCartElements();
        model.addAttribute("cartItems", cartElementDTOList);
        model.addAttribute("customer", customer);
        return "cart";
    }

    @GetMapping("add/product/{id}")
    public String addCartElement(@PathVariable("id") Long id) {
        ProductDTO product = productService.getProductById(id);
        cartService.addCartElement(product);
        return "redirect:/catalog";
    }

    @GetMapping("/remove")
    public String removeCartElement(@RequestParam("element_Id") Long id) {
        cartService.delete(id);
        return "redirect:/cart";
    }

    @GetMapping("/change")
    public String increaseCartElementsAmount(@RequestParam("element_Id") Long id, @RequestParam("quantity") Integer quantity) {
        cartService.updateCartElement(id, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/confirmation")
    public String confirmCart(Model model, OrderInfoDTO orderInfo){
        CustomerDTO customer = customerService.getCustomer();
        model.addAttribute("address", customer.getCustomerAddress());
        model.addAttribute("customer", customer);
        model.addAttribute("cart", customer.getCart());
        model.addAttribute("cartElements", cartService.getCartElements());
        model.addAttribute("order", orderInfo);
        return "checkout";
    }
}

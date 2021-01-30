package com.javaschool.onlineshop.controllers;


import com.javaschool.onlineshop.model.dto.CustomerDTO;
import com.javaschool.onlineshop.model.dto.CartElementDTO;
import com.javaschool.onlineshop.model.dto.ProductDTO;
import com.javaschool.onlineshop.model.dto.OrderInfoDTO;
import com.javaschool.onlineshop.service.CartService;
import com.javaschool.onlineshop.service.CustomerService;
import com.javaschool.onlineshop.service.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * This class is responsible for handling user actions with the shopping cart.
 */
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

    /**
     * The method is used to return all items added to the cart
     *
     * @param model             will be sent to the view
     * @return cart view
     */
    @GetMapping
    public String getAllItemsInCart(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomerDTO customer = customerService.getByUsername(authentication.getName());
        List<CartElementDTO> cartElementDTOList = cartService.getCartElements();
        model.addAttribute("cartItems", cartElementDTOList);
        model.addAttribute("customer", customer);
        return "cart";
    }

    /**
     * Method for adding a product with the specified id to the cart
     *
     * @param id            identifier of product to be sent to the cart
     * @return redirect to catalog view
     */
    @GetMapping("/product/{id}")
    public String addCartElement(@PathVariable("id") Long id) {
        ProductDTO product = productService.getProductById(id);
        cartService.addCartElement(product);
        return "redirect:/catalog";
    }

    /**
     * Method for decreasing the amount of a specific product in the cart.
     *
     * @param id            identifier of the product to be decreased
     * @return redirect to cart view
     */
    @GetMapping("/deletion")
    public String removeCartElement(@RequestParam("element_Id") Long id) {
        cartService.deleteCartElement(id);
        return "redirect:/cart";
    }

    /**
     * Method for increasing the quantity of a specific product in the shopping cart.
     *
     * @param id            identifier of the product to be increased
     * @param quantity      updated quantity of product in the cart
     * @return redirect to cart view
     */
    @GetMapping("/modification")
    public String increaseCartElementsAmount(@RequestParam("element_Id") Long id, @RequestParam("quantity") Integer quantity) {
        cartService.updateCartElement(id, quantity);
        return "redirect:/cart";
    }

    /**
     * Method for sending user-entered information to the confirmation page.
     *
     * @param model             will be sent to the checkout view
     * @param orderInfo         order information to be filled in on the checkout page
     * @return checkout view
     */
    @GetMapping("/confirmation")
    public String confirmCart(Model model, OrderInfoDTO orderInfo) {
        CustomerDTO customer = customerService.getCustomer();
        model.addAttribute("address", customer.getCustomerAddress());
        model.addAttribute("customer", customer);
        model.addAttribute("cart", customer.getCart());
        model.addAttribute("cartElements", cartService.getCartElements());
        model.addAttribute("order", orderInfo);
        return "checkout";
    }
}

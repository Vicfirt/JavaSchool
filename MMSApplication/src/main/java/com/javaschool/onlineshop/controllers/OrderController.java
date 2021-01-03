package com.javaschool.onlineshop.controllers;


import com.javaschool.onlineshop.model.dto.CartElementDTO;
import com.javaschool.onlineshop.model.dto.CustomerDTO;
import com.javaschool.onlineshop.model.dto.OrderInfoDTO;
import com.javaschool.onlineshop.service.CartService;
import com.javaschool.onlineshop.service.CustomerService;
import com.javaschool.onlineshop.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * This class implements the logic of transition to a specific page
 * associated with orders in accordance with the received URL.
 */
@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    private final CartService cartService;

    private final CustomerService customerService;


    public OrderController(OrderService orderService, CartService cartService, CustomerService customerService) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.customerService = customerService;
    }

    /**
     * If the url address points to an order, then a list of orders for
     * further work will be transferred to the display page.
     */
    @GetMapping("/customer/all")
    public String getAllOrders(Model model) {
        CustomerDTO customer = customerService.getCustomer();
        List<OrderInfoDTO> orderInfoList = orderService.findAllOrders(customer.getCustomerId());
        model.addAttribute("orders", orderInfoList);
        model.addAttribute("customer", customer);
        return "order_info";
    }


    @GetMapping("/customer/new")
    public String createOrder() {
        List<CartElementDTO> cartElementList = cartService.getCartElements();
        orderService.createOrder(cartElementList);
        return "redirect: orders/customer/all";
    }
}

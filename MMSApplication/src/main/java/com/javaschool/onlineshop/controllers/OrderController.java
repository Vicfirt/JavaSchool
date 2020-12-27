package com.javaschool.onlineshop.controllers;


import com.javaschool.onlineshop.dto.CartElementDTO;
import com.javaschool.onlineshop.dto.OrderInfoDTO;
import com.javaschool.onlineshop.service.CartService;
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


    public OrderController(OrderService orderService, CartService cartService) {
        this.orderService = orderService;
        this.cartService = cartService;
    }

    /**
     * If the url address points to an order, then a list of orders for
     * further work will be transferred to the display page.
     */
    @GetMapping("/all")
    public String getAllOrders(Model model) {
        List<OrderInfoDTO> orderInfoList = orderService.findAllOrders(1L);
        model.addAttribute("orders", orderInfoList);
        model.addAttribute("counter", cartService.getCart().getCartTotal());
        return "order_info";
    }

    //It`s temporary solution while we don`t have authorized customer
    @GetMapping("/order")
    public String createOrder() {
        List<CartElementDTO> cartElementList = cartService.getCartElements();
        orderService.createOrder(cartElementList);
        return "redirect:/all";
    }
}

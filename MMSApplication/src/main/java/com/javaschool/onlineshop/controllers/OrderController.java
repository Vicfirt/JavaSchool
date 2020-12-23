package com.javaschool.onlineshop.controllers;


import com.javaschool.onlineshop.service.OrderService;
import com.javaschool.onlineshop.entity.OrderInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * This class implements the logic of transition to a specific page
 * associated with orders in accordance with the received URL.
 */
@Controller
public class OrderController {

    public OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * If the url address points to an order, then a list of orders for
     * further work will be transferred to the display page.
     */
    @GetMapping("/orders")
    public String getAllOrders(Model model) {
        List<OrderInfo> ordersInfo = orderService.findAll();
        model.addAttribute("orders", ordersInfo);
        return "orders_info";
    }
}

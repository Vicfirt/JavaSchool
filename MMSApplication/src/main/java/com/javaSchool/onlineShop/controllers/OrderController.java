package com.javaSchool.onlineShop.controllers;


import com.javaSchool.onlineShop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This class implements the logic of transition to a specific page
 * associated with orders in accordance with the received URL.
 */


@Controller
public class OrderController {


    public OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     *If the url address points to an order, then a list of orders for
     * further work will be transferred to the display page.
     */

    @GetMapping("/orders")
    public String getAllOrders(Model model){

        model.addAttribute("orders",orderService.findAll());

        return "orders_list";
    }


}

package com.nikitin.javaSchool.controllers;


import com.nikitin.javaSchool.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrderController {

    @Autowired
    public OrderService orderService;




    @GetMapping("/hello-world")
    public String sayHello(){

        return "hello_world";
    }

    @GetMapping("/orders")
    public String getAllOrders(Model model){

        model.addAttribute("orders",orderService.findAll());

        return "orders_list";
    }


}

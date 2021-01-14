package com.javaschool.onlineshop.controllers;


import com.javaschool.onlineshop.model.dto.CustomerDTO;
import com.javaschool.onlineshop.model.dto.OrderInfoDTO;
import com.javaschool.onlineshop.service.CartService;
import com.javaschool.onlineshop.service.CustomerService;
import com.javaschool.onlineshop.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the logic of transition to a specific page
 * associated with orders in accordance with the received URL.
 */
@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    private final CustomerService customerService;

    private final CartService cartService;

    public OrderController(OrderService orderService, CustomerService customerService, CartService cartService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.cartService = cartService;
    }

    /**
     * If the url address points to an order, then a list of orders for
     * further work will be transferred to the display page.
     */
    @GetMapping("/all")
    public String getAllCustomerOrders(Model model) {
        CustomerDTO customer = customerService.getCustomer();
        List<OrderInfoDTO> orderInfoList = orderService.findAllCustomerOrders();
        model.addAttribute("orders", orderInfoList);
        model.addAttribute("customer", customer);
        return "order_info";
    }

    @PostMapping("/order/new")
    public String createOrder(@RequestParam("paymentMethodId") Integer paymentMethodId,
                              @Valid @ModelAttribute("order") OrderInfoDTO orderInfo) {
        orderInfo.setPaymentMethodId(paymentMethodId);
        orderService.addOrder(orderInfo);
        return "redirect:/orders/all";
    }

    @GetMapping
    public String findAllOrdersDetails(Model model) {
        List<OrderInfoDTO> orderInfoList = orderService.findAllOrders();
        CustomerDTO customer = customerService.getCustomer();
        List<CustomerDTO> customersList = new ArrayList<>();
        for (OrderInfoDTO order : orderInfoList) {
            customersList.add(customerService.getById(order.getCustomerId()));
        }
        model.addAttribute("orders", orderInfoList);
        model.addAttribute("customers", customersList);
        model.addAttribute("customer", customer);
        return "order_info";
    }

    @GetMapping("/status")
    public String changeOrderStatus(@RequestParam("statusId") Integer statusId,
                                    @RequestParam("orderId") Long orderId) {
        OrderInfoDTO orderInfo = orderService.get(orderId);
        orderInfo.setStatusId(statusId);
        orderService.updateOrder(orderInfo);
        return "redirect:/orders";
    }
}

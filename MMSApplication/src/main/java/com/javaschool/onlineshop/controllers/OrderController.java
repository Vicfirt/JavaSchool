package com.javaschool.onlineshop.controllers;


import com.javaschool.onlineshop.model.dto.CustomerDTO;
import com.javaschool.onlineshop.model.dto.OrderElementDTO;
import com.javaschool.onlineshop.model.dto.OrderInfoDTO;
import com.javaschool.onlineshop.service.CustomerService;
import com.javaschool.onlineshop.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for handling actions with orders.
 */
@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    private final CustomerService customerService;

    public OrderController(OrderService orderService, CustomerService customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
    }

    /**
     * This method is used to return all user orders.
     */
    @GetMapping("/all")
    public String getAllCustomerOrders(Model model) {
        CustomerDTO customer = customerService.getCustomer();
        List<OrderInfoDTO> orderInfoList = orderService.findAllCustomerOrders();
        model.addAttribute("orders", orderInfoList);
        model.addAttribute("customer", customer);
        return "order_info";
    }

    /**
     * This method is used to create a new order by the user.
     * @param paymentMethodId           user-selected payment method
     * @param orderInfo                 order received from the form
     * @return redirect to order info view
     */
    @PostMapping("/order/new")
    public String createOrder(@RequestParam("paymentMethodId") Integer paymentMethodId,
                              @Valid @ModelAttribute("order") OrderInfoDTO orderInfo) {
        orderInfo.setPaymentMethodId(paymentMethodId);
        orderService.addOrder(orderInfo);
        return "redirect:/orders/all";
    }

    /**
     * Method to return all orders in online shop.
     * @param model                     this will be sent to the view
     * @return order info view
     */
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

    /**
     * Method for changing the status of any order.
     * @param statusId              status
     * @param orderId               it specifies the order to set status
     * @return redirect to order info view
     */
    @GetMapping("/status")
    public String changeOrderStatus(@RequestParam("statusId") Integer statusId,
                                    @RequestParam("orderId") Long orderId) {
        OrderInfoDTO orderInfo = orderService.get(orderId);
        orderInfo.setStatusId(statusId);
        orderService.updateOrder(orderInfo);
        return "redirect:/orders";
    }

    /**
     * This method is used to return a receipt for an specified order.
     * @param orderId               specifies the order
     * @param model                 this will be sent to the view
     * @return receipt view
     */
    @GetMapping("/receipt/{orderId}")
    public String showReceipt(@PathVariable("orderId") Long orderId,
                              Model model) {
        CustomerDTO customer = customerService.getCustomer();
        List<OrderElementDTO> orderElements = orderService.getAllOrderElements(orderId);
        OrderInfoDTO orderInfoDTO = orderService.get(orderId);
        model.addAttribute("orders", orderElements);
        model.addAttribute("order", orderInfoDTO);
        model.addAttribute("customer", customer);
        return "receipt";
    }

    /**
     * This method is responsible for deleting the specified order.
     * @param orderId           specifies the order to be deleted
     * @param model              this will be sent to the view
     * @return redirect to order info view
     */
    @GetMapping("/deletion/{orderId}")
    public String deleteOrder(@PathVariable("orderId") Long orderId,
                              Model model) {
        orderService.deleteOrder(orderId);
        return "redirect:/orders";
    }
}

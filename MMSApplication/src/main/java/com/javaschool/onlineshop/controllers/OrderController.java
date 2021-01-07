package com.javaschool.onlineshop.controllers;


import com.javaschool.onlineshop.model.dto.CustomerDTO;
import com.javaschool.onlineshop.model.dto.OrderInfoDTO;
import com.javaschool.onlineshop.service.CustomerService;
import com.javaschool.onlineshop.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;
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

    public OrderController(OrderService orderService, CustomerService customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
    }

    /**
     * If the url address points to an order, then a list of orders for
     * further work will be transferred to the display page.
     */
    @GetMapping("/all") //Еще добавятся методы для управления заказами, поэтому имена понадобятся
    public String getAllOrders(Model model) {
        CustomerDTO customer = customerService.getCustomer();//Не убрал так как требутеся отдельный кастомер для отрисовки хэдыра
        List<OrderInfoDTO> orderInfoList = orderService.findAllOrders();//Тут спрятал вызов кастомера в сервис
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
}

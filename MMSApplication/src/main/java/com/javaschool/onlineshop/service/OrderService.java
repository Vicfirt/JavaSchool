package com.javaschool.onlineshop.service;

import com.javaschool.onlineshop.model.dto.OrderInfoDTO;

import java.util.List;

public interface OrderService {

    List<OrderInfoDTO> findAllCustomerOrders();

    void addOrder(OrderInfoDTO orderInfoDTO);

    List<OrderInfoDTO> findAllOrders();

    void updateOrder(OrderInfoDTO orderInfoDTO);

    OrderInfoDTO get(Long orderId);
}

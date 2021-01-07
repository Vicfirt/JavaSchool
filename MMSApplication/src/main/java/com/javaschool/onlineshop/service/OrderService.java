package com.javaschool.onlineshop.service;

import com.javaschool.onlineshop.model.dto.OrderInfoDTO;

import java.util.List;

public interface OrderService {

    List<OrderInfoDTO> findAllOrders();

    void addOrder(OrderInfoDTO orderInfoDTO);
}

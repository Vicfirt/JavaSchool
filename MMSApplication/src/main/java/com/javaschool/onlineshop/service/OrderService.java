package com.javaschool.onlineshop.service;


import com.javaschool.onlineshop.dto.CartElementDTO;
import com.javaschool.onlineshop.dto.OrderInfoDTO;

import java.util.List;

public interface OrderService {

    void createOrder(List<CartElementDTO> elementList);

    List<OrderInfoDTO> findAllOrders(Long customerId);
}

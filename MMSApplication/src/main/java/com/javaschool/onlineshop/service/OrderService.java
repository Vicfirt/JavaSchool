package com.javaschool.onlineshop.service;


import com.javaschool.onlineshop.model.dto.CartElementDTO;
import com.javaschool.onlineshop.model.dto.OrderInfoDTO;

import java.util.List;

public interface OrderService {

    List<OrderInfoDTO> findAllOrders(Long customerId);

    void addOrder(OrderInfoDTO orderInfoDTO, List<CartElementDTO> elementList);
}

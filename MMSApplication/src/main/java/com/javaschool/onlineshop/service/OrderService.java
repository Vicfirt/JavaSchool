package com.javaschool.onlineshop.service;

import com.javaschool.onlineshop.entity.CartElement;
import com.javaschool.onlineshop.entity.OrderInfo;

import java.util.List;

public interface OrderService {

    void createOrder(List<CartElement> elementList);

    List<OrderInfo> findAllOrders(Long customerId);
}

package com.javaschool.onlineshop.service;

import com.javaschool.onlineshop.entity.OrderInfo;

import java.util.List;

public interface OrderService {

    List<OrderInfo> findAll();
}

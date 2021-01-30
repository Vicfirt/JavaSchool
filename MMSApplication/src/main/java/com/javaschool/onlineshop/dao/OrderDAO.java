package com.javaschool.onlineshop.dao;


import com.javaschool.onlineshop.model.entity.OrderInfo;

import java.util.List;

public interface OrderDAO {

    List<OrderInfo> findAllCustomerOrders(Long customerId);

    void add(OrderInfo orderInfo);

    void delete(Long id);

    void update(OrderInfo orderInfo);

    OrderInfo get(Long id);

    List<OrderInfo> findAllOrders();
}



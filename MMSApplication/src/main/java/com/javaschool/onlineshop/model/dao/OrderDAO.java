package com.javaschool.onlineshop.model.dao;


import com.javaschool.onlineshop.entity.OrderInfo;

import java.util.List;

public interface OrderDAO {

    List<OrderInfo> findAllOrders(Long customerId);

    void add(OrderInfo orderInfo);

    void delete(Long id);

    void update(OrderInfo orderInfo);

    OrderInfo get(Long id);

    boolean checkIfExists(Long id);
}



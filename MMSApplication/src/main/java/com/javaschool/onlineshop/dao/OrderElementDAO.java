package com.javaschool.onlineshop.dao;


import com.javaschool.onlineshop.model.entity.OrderElement;

import java.util.List;

public interface OrderElementDAO {

    void add(OrderElement orderElement);

    List<OrderElement> getAllOrderElements(Long orderId);

    void deleteOrderElementsByOrder(Long orderId);
}


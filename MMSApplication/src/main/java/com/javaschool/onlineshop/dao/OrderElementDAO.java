package com.javaschool.onlineshop.dao;


import com.javaschool.onlineshop.model.entity.OrderElement;
import java.util.List;

public interface OrderElementDAO {

    void add(OrderElement orderElement);

    List<OrderElement> getElementsInOrder(Long orderInfo);

    OrderElement get(Long id);
}


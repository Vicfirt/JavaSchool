package com.javaschool.onlineshop.dao;


import com.javaschool.onlineshop.entity.OrderElement;
import java.util.List;

public interface OrderElementDAO {

    void add(OrderElement orderElement);

    void delete(Long id);

    void update(OrderElement orderElement);

    List<OrderElement> getElementsInOrder(Long orderInfo);

    OrderElement get(Long id);
}


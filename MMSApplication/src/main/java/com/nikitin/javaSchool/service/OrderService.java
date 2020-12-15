package com.nikitin.javaSchool.service;

import com.nikitin.javaSchool.entity.Order;

import java.util.List;

public interface OrderService {

    void save(Order order);

    Order getOrderById(int id);

    void delete(int id);

    void update(Order order);

    List<Order> findAll();




}

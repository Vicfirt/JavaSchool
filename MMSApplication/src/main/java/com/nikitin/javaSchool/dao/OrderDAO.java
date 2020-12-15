package com.nikitin.javaSchool.dao;

import com.nikitin.javaSchool.entity.Order;

import java.util.List;

public interface OrderDAO {

    void save(Order order);

    Order getOrderById(int id);

    void delete(int id);

    void update(Order order);

    List<Order> findAll();


}



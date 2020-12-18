package com.javaschool.onlineshop.dao;

import com.javaschool.onlineshop.entity.Order;

import java.util.List;

public interface OrderDAO {

    List<Order> findAll();
}



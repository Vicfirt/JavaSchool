package com.javaschool.onlineshop.dao;

import com.javaschool.onlineshop.entity.OrderInfo;

import java.util.List;

public interface OrderDAO {

    List<OrderInfo> findAll();
}



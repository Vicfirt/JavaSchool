package com.javaSchool.onlineShop.service.impl;

import com.javaSchool.onlineShop.entity.Order;
import com.javaSchool.onlineShop.dao.OrderDAO;
import com.javaSchool.onlineShop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    public OrderDAO orderDAO;

    @Override
    @Transactional
    public List<Order> findAll() {
        return orderDAO.findAll();
    }



}

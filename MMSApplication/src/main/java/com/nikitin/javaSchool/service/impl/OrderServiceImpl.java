package com.nikitin.javaSchool.service.impl;

import com.nikitin.javaSchool.dao.OrderDAO;
import com.nikitin.javaSchool.entity.Order;
import com.nikitin.javaSchool.service.OrderService;
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

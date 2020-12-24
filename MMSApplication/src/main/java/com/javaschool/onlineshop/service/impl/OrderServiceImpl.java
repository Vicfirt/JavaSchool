package com.javaschool.onlineshop.service.impl;

import com.javaschool.onlineshop.dao.OrderDAO;
import com.javaschool.onlineshop.service.OrderService;
import com.javaschool.onlineshop.entity.OrderInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;

    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    @Transactional
    public List<OrderInfo> findAll() {
        return orderDAO.findAll();
    }
}

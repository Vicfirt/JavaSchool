package com.nikitin.javaSchool.service.serviceImplementations;

import com.nikitin.javaSchool.dao.OrderDAO;
import com.nikitin.javaSchool.entity.Order;
import com.nikitin.javaSchool.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    public OrderDAO orderDAO;

    @Override
    public List<Order> findAll() {
        return orderDAO.findAll();
    }

    @Override
    public void save(Order order) {
        orderDAO.save(order);
    }

    @Override
    public Order getOrderById(int id) {
        return orderDAO.getOrderById(id);
    }

    @Override
    public void delete(int id) {
        orderDAO.delete(id);

    }

    @Override
    public void update(Order order) {
        orderDAO.update(order);

    }
}

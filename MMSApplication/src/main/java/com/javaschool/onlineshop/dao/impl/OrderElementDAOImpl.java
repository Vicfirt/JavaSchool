package com.javaschool.onlineshop.dao.impl;

import com.javaschool.onlineshop.dao.OrderElementDAO;
import com.javaschool.onlineshop.entity.OrderElement;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderElementDAOImpl implements OrderElementDAO {

    private final SessionFactory sessionFactory;

    public OrderElementDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(OrderElement orderElement) {
        sessionFactory.getCurrentSession().persist(orderElement);

    }

    @Override
    public void delete(Long id) {


    }

    @Override
    public void update(OrderElement orderElement) {

    }

    @Override
    public List<OrderElement> getElementsInOrder(Long orderInfo) {
        return null;
    }

    @Override
    public OrderElement get(Long id) {
        return null;
    }
}

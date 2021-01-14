package com.javaschool.onlineshop.dao.impl;


import com.javaschool.onlineshop.dao.OrderElementDAO;
import com.javaschool.onlineshop.model.entity.OrderElement;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;


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
}

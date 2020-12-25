package com.javaschool.onlineshop.dao.impl;

import com.javaschool.onlineshop.dao.OrderDAO;
import com.javaschool.onlineshop.entity.OrderInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {

    public SessionFactory sessionFactory;

    public OrderDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<OrderInfo> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from OrderInfo").list();
    }
}

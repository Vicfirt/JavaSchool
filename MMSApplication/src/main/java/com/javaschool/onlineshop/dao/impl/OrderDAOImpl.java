package com.javaschool.onlineshop.dao.impl;


import com.javaschool.onlineshop.exception.DataNotFoundException;
import com.javaschool.onlineshop.dao.OrderDAO;
import com.javaschool.onlineshop.model.entity.OrderInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class is responsible for getting data from order info database entity.
 */
@Repository
public class OrderDAOImpl implements OrderDAO {

    private final SessionFactory sessionFactory;

    public OrderDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<OrderInfo> findAllCustomerOrders(Long customerId) {
        Session session = sessionFactory.getCurrentSession();
        String myQuery = "FROM OrderInfo where customerId = :id";
        return session.createQuery(myQuery, OrderInfo.class)
                .setParameter("id", customerId)
                .getResultList();
    }

    @Override
    public List<OrderInfo> findAllOrders() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM OrderInfo").list();
    }

    @Override
    public void add(OrderInfo orderInfo) {
        sessionFactory.getCurrentSession().persist(orderInfo);
    }

    @Override
    public void delete(Long id) {
        OrderInfo orderInfo = sessionFactory.getCurrentSession().get(OrderInfo.class, id);
        if (orderInfo == null) throw new DataNotFoundException("There is no order with id: " + id);
        sessionFactory.getCurrentSession().delete(orderInfo);
    }

    @Override
    public void update(OrderInfo orderInfo) {
        sessionFactory.getCurrentSession().update(orderInfo);
    }

    @Override
    public OrderInfo get(Long id) {
        String myQuery = "FROM OrderInfo WHERE id = :id";
        Session session = sessionFactory.getCurrentSession();
        OrderInfo orderInfo = session.createQuery(myQuery, OrderInfo.class)
                .setParameter("id", id)
                .getSingleResult();
        if (orderInfo == null) throw new DataNotFoundException("There is not order with id " + id);
        return orderInfo;
    }
}

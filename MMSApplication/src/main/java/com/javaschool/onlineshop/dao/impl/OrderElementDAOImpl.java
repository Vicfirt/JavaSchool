package com.javaschool.onlineshop.dao.impl;


import com.javaschool.onlineshop.dao.OrderElementDAO;
import com.javaschool.onlineshop.model.entity.OrderElement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class is responsible for getting data from order element database entity.
 */
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
    public List<OrderElement> getAllOrderElements(Long orderId) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM OrderElement WHERE orderId = :orderId";
        return session.createQuery(query, OrderElement.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }

    @Override
    public void deleteOrderElementsByOrder(Long orderId) {
        sessionFactory.getCurrentSession().createQuery("delete from OrderElement where orderId =:orderId")
                .setParameter("orderId", orderId)
                .executeUpdate();
    }
}


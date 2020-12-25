package com.javaschool.onlineshop.dao.impl;

import com.javaschool.onlineshop.dao.OrderDAO;
import com.javaschool.onlineshop.entity.OrderInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {

    private final SessionFactory sessionFactory;

    public OrderDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<OrderInfo> findAllOrders(Long customerId) {
        Session session = sessionFactory.getCurrentSession();
        String myQuery = "FROM OrderInfo where customerId = :id";
        Query query = session.createQuery(myQuery);
        query.setParameter("id", customerId );
        return query.getResultList();
    }

    @Override
    public void add(OrderInfo orderInfo) {
        sessionFactory.getCurrentSession().persist(orderInfo);
    }

    @Override
    public void delete(Long id) {
        OrderInfo orderInfo = sessionFactory.getCurrentSession().get(OrderInfo.class, id);
        sessionFactory.getCurrentSession().delete(orderInfo);

    }

    @Override
    public void update(OrderInfo orderInfo) {
        sessionFactory.getCurrentSession().update(orderInfo);

    }

    @Override
    public OrderInfo get(Long id) {
        String myQuery = "from OrderInfo WHERE id = :id";
        Query query = sessionFactory.getCurrentSession().createQuery(myQuery);
        query.setParameter("id", id);
        return (OrderInfo)query.getSingleResult();
    }

    @Override
    public boolean checkIfExists(Long id) {
        String myQuery = "FROM OrderInfo WHERE id = :id";
        Query query = sessionFactory.getCurrentSession().createQuery(myQuery);
        query.setParameter("id", id);
        return query.getSingleResult() != null;
    }
}

package com.nikitin.javaSchool.dao.impl;

import com.nikitin.javaSchool.dao.OrderDAO;
import com.nikitin.javaSchool.entity.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {

    public SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Order> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Order").list();
    }

}

package com.javaschool.onlineshop.dao.impl;

import com.javaschool.onlineshop.dao.PurchaseDAO;
import com.javaschool.onlineshop.entity.Purchase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PurchaseDAOImpl implements PurchaseDAO {

    public SessionFactory sessionFactory;


    public PurchaseDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Purchase> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Purchase").list();
    }
}

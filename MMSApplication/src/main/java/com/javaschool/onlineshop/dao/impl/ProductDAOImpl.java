package com.javaschool.onlineshop.dao.impl;


import com.javaschool.onlineshop.dao.ProductDAO;
import com.javaschool.onlineshop.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    public SessionFactory sessionFactory;

    @Autowired
    public ProductDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Product> findAll(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Product ").list();
    }

    @Override
    public Product getProductById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Product.class,id);
    }
}

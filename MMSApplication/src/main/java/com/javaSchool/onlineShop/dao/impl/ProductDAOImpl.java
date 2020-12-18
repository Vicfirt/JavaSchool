package com.javaSchool.onlineShop.dao.impl;


import com.javaSchool.onlineShop.dao.ProductDAO;
import com.javaSchool.onlineShop.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    public SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
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

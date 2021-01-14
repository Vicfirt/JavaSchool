package com.javaschool.onlineshop.dao.impl;


import com.javaschool.onlineshop.dao.CartDAO;
import com.javaschool.onlineshop.model.entity.Cart;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CartDAOImpl implements CartDAO {

    private final SessionFactory sessionFactory;

    public CartDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void updateCart(Cart cart) {
        sessionFactory.getCurrentSession().update(cart);
    }

    @Override
    public void addCart(Cart cart) {
        sessionFactory.getCurrentSession().persist(cart);
    }
}

package com.javaschool.onlineshop.dao.impl;


import com.javaschool.onlineshop.dao.CartDAO;
import com.javaschool.onlineshop.model.entity.Cart;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 * This class is responsible for getting data from cart database entity.
 */
@Repository
public class CartDAOImpl implements CartDAO {

    private final SessionFactory sessionFactory;

    public CartDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Cart updateCart(Cart cart) {
        sessionFactory.getCurrentSession().update(cart);
        return cart;
    }

    @Override
    public Cart addCart(Cart cart) {
        sessionFactory.getCurrentSession().persist(cart);
        return cart;
    }
}

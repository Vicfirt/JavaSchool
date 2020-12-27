package com.javaschool.onlineshop.dao.impl;


import com.javaschool.onlineshop.dao.CartDAO;
import com.javaschool.onlineshop.entity.Cart;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class CartDAOImpl implements CartDAO {

    private final SessionFactory sessionFactory;

    public CartDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Cart getCart(Long customerId) {
        String dbQuery = "FROM Cart WHERE customer = :customerId";
        Query query = sessionFactory.getCurrentSession().createQuery(dbQuery, Cart.class);
        query.setParameter("customerId", customerId);
        return (Cart) query.getSingleResult();
    }

    @Override
    public boolean updateCart(Cart cart) {
        try {
            sessionFactory.getCurrentSession().update(cart);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean addCart(Cart cart){
        try {
            sessionFactory.getCurrentSession().persist(cart);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public Cart getCartById(Long cartId){
        String dbQuery = "FROM Cart WHERE cartId = :cartId";
        Query query = sessionFactory.getCurrentSession().createQuery(dbQuery, Cart.class);
        query.setParameter("cartId", cartId);
        return (Cart) query.getSingleResult();
    }
}

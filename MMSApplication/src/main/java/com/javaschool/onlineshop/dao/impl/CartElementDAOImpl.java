package com.javaschool.onlineshop.dao.impl;


import com.javaschool.onlineshop.dao.CartElementDAO;
import com.javaschool.onlineshop.entity.Cart;
import com.javaschool.onlineshop.entity.CartElement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CartElementDAOImpl implements CartElementDAO {

    SessionFactory sessionFactory;


    @Override
    public CartElement getCart(int id) {
        return sessionFactory.getCurrentSession().get(CartElement.class, id);
    }

    @Override
    public boolean add(CartElement cartElement) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(cartElement);
        return true;
    }

    @Override
    public boolean update(CartElement cartElement) {
        Session session = sessionFactory.getCurrentSession();
        session.update(cartElement);
        return true;
    }

    @Override
    public boolean delete(CartElement cartElement) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(cartElement);
        return true;
    }

    @Override
    public List<CartElement> cartList(int cartId) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM CartElement WHERE cartId = :cartId";
        return session
                .createQuery(query, CartElement.class)
                .setParameter("cartId", cartId)
                .getResultList();
    }

    @Override
    public boolean updateCart(Cart cart) {
        Session session = sessionFactory.getCurrentSession();
        session.update(cart);
        return true;
    }
}

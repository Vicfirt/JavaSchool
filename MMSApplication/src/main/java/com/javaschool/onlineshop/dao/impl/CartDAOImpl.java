package com.javaschool.onlineshop.dao.impl;


import com.javaschool.onlineshop.dao.CartDAO;
import com.javaschool.onlineshop.entity.Cart;
import com.javaschool.onlineshop.entity.CartElement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDAOImpl implements CartDAO {

    SessionFactory sessionFactory;


    @Override
    public CartElement getCartElement(int id) {
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
    public List<CartElement> cartList() {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM CartElement";
        return session.createQuery(query, CartElement.class).list();
    }

    @Override
    public boolean updateCart(Cart cart) {
        Session session = sessionFactory.getCurrentSession();
        session.update(cart);
        return true;
    }
}

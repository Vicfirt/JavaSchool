package com.javaschool.onlineshop.dao.impl;


import com.javaschool.onlineshop.dao.CartElementDAO;
import com.javaschool.onlineshop.entity.CartElement;
import com.javaschool.onlineshop.entity.Product;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartElementDAOImpl implements CartElementDAO {

    private final SessionFactory sessionFactory;

    public CartElementDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public CartElement get(Long id) {

        return sessionFactory.getCurrentSession().get(CartElement.class, id);
    }

    public boolean add(CartElement cartElement) {
        try {

            sessionFactory.getCurrentSession().persist(cartElement);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Long id) {
        try {
            CartElement cartElement = sessionFactory.getCurrentSession().get(CartElement.class, id);
            sessionFactory.getCurrentSession().delete(cartElement);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(CartElement cartElement) {
        try {
            sessionFactory.getCurrentSession().update(cartElement);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<CartElement> listAll(Long cartId) {
        try {
            String elements = "FROM CartElement where cartId = :cartId";
            Query query = sessionFactory.getCurrentSession().createQuery(elements);
            query.setParameter("cartId", cartId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<CartElement> listAvailable(Long cartId) {

        return null;
    }

    public CartElement get(Long cartId, Product product) {
        return null;
    }
}

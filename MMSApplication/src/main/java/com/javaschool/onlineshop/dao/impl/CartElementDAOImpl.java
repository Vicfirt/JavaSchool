package com.javaschool.onlineshop.dao.impl;


import com.javaschool.onlineshop.dao.CartElementDAO;
import com.javaschool.onlineshop.model.entity.CartElement;
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

    public void add(CartElement cartElement) {
            sessionFactory.getCurrentSession().persist(cartElement);
    }

    public void delete(Long id) {
            CartElement cartElement = sessionFactory.getCurrentSession().get(CartElement.class, id);
            sessionFactory.getCurrentSession().delete(cartElement);
    }

    @Override
    public void update(CartElement cartElement) {
            sessionFactory.getCurrentSession().update(cartElement);
    }

    public List<CartElement> findAll(Long cartId) {
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
}

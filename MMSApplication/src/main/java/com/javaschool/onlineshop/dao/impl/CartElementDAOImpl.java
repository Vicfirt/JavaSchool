package com.javaschool.onlineshop.dao.impl;


import com.javaschool.onlineshop.Exception.MyException;
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
        try {
            return sessionFactory.getCurrentSession().get(CartElement.class, id);
        } catch (Exception e) {
            throw new MyException(31, "Cart element with this id does not exist");
        }
    }

    public void add(CartElement cartElement) {
        sessionFactory.getCurrentSession().persist(cartElement);
    }

    public void delete(Long id) {
        try {
            CartElement cartElement = sessionFactory.getCurrentSession().get(CartElement.class, id);
            sessionFactory.getCurrentSession().delete(cartElement);
        } catch (Exception e) {
            throw new MyException(31, "Cart element with this id does not exist");
        }
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
            throw new MyException(30, "Cart with this id is empty");
        }
    }
}

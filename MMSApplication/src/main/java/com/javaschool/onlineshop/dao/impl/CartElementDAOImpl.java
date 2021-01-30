package com.javaschool.onlineshop.dao.impl;


import com.javaschool.onlineshop.exception.DataNotFoundException;
import com.javaschool.onlineshop.dao.CartElementDAO;
import com.javaschool.onlineshop.model.entity.CartElement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class is responsible for getting data from cart element database entity.
 */
@Repository
public class CartElementDAOImpl implements CartElementDAO {

    private final SessionFactory sessionFactory;

    public CartElementDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public CartElement get(Long id) {
            CartElement cartElement = sessionFactory.getCurrentSession().get(CartElement.class, id);
            if(cartElement == null) throw new DataNotFoundException("Cart element with id: "+ id + " does not exist");
            return cartElement;
    }

    @Override
    public CartElement add(CartElement cartElement) {
        sessionFactory.getCurrentSession().persist(cartElement);
        return cartElement;
    }

    @Override
    public CartElement delete(Long id) {
            CartElement cartElement = sessionFactory.getCurrentSession().get(CartElement.class, id);
            if(cartElement == null) throw new DataNotFoundException("Cart element with id: "+ id + " does not exist");
            sessionFactory.getCurrentSession().delete(cartElement);
            return cartElement;
    }

    @Override
    public CartElement update(CartElement cartElement) {
        sessionFactory.getCurrentSession().update(cartElement);
        return cartElement;
    }

    @Override
    public List<CartElement> findAll(Long cartId) {
            String query = "FROM CartElement where cartId = :cartId";
            Session session  = sessionFactory.getCurrentSession();
            return session.createQuery(query, CartElement.class)
                    .setParameter("cartId", cartId)
                    .getResultList();
    }
}

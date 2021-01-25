package com.javaschool.onlineshop.dao.impl;


import com.javaschool.onlineshop.exception.DataNotFoundException;
import com.javaschool.onlineshop.dao.CartElementDAO;
import com.javaschool.onlineshop.model.entity.CartElement;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
            if(cartElement == null) throw new DataNotFoundException("Cart element with this id does not exist");
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
            if(cartElement == null) throw new DataNotFoundException("Cart element with this id does not exist");
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
            String elements = "FROM CartElement where cartId = :cartId";
            Query query = sessionFactory.getCurrentSession().createQuery(elements);
            query.setParameter("cartId", cartId);
            return query.getResultList();
    }
}

package com.javaschool.onlineshop.dao.impl;


import com.javaschool.onlineshop.exception.DataNotFoundException;
import com.javaschool.onlineshop.dao.CustomerDAO;
import com.javaschool.onlineshop.model.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;

/**
 * This class is responsible for getting data from customer database entity.
 */
@Repository
public class CustomerDAOImpl implements CustomerDAO {

    private final SessionFactory sessionFactory;

    public CustomerDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Customer addCustomer(Customer customer) {
            sessionFactory.getCurrentSession().persist(customer);
            return customer;
    }

    @Override
    public Customer get(Long id) {
        String selectQuery = "FROM Customer WHERE customerId = :id";
            Session session = sessionFactory.getCurrentSession();
            Customer customer =  session.createQuery(selectQuery, Customer.class)
                    .setParameter("id", id)
                    .getSingleResult();
            if (customer == null) throw new DataNotFoundException("The user with the given id does not exist");
            return customer;
    }

    @Override
    @Transactional
    public Customer getByUsername(String username) {
            String query = "FROM Customer WHERE customerEmailAddress = :username";
            try {
                Customer customer = sessionFactory.getCurrentSession().createQuery(query, Customer.class)
                        .setParameter("username", username).getSingleResult();
                return customer;
            }
            catch (NoResultException exception){
                return null;
            }
    }

    @Override
    public void update(Customer customer) {
        sessionFactory.getCurrentSession().update(customer);
    }
}

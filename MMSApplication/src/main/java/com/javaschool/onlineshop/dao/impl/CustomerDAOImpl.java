package com.javaschool.onlineshop.dao.impl;


import com.javaschool.onlineshop.dao.CustomerDAO;
import com.javaschool.onlineshop.model.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    private final SessionFactory sessionFactory;

    public CustomerDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addCustomer(Customer customer) {
        sessionFactory.getCurrentSession().persist(customer);
    }

    @Override
    public Customer get(Long id) {

        String selectQuery = "FROM Customer WHERE customerId = :id";
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery(selectQuery, Customer.class)
                    .setParameter("id", id)
                    .getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public Customer getByUsername(String username) {
        try {
            String query = "FROM Customer WHERE customerEmailAddress = :username";

            return sessionFactory.getCurrentSession().createQuery(query, Customer.class)
                    .setParameter("username", username).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Long customerId) {
        Customer customer = sessionFactory.getCurrentSession().get(Customer.class, customerId);
        sessionFactory.getCurrentSession().delete(customer);
    }

    @Override
    public void update(Customer customer) {
            sessionFactory.getCurrentSession().update(customer);
    }
}

package com.javaschool.onlineshop.dao.impl;


import com.javaschool.onlineshop.dao.CustomerDAO;
import com.javaschool.onlineshop.model.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    private final SessionFactory sessionFactory;

    private final BCryptPasswordEncoder passwordEncoder;

    public CustomerDAOImpl(SessionFactory sessionFactory, BCryptPasswordEncoder passwordEncoder) {
        this.sessionFactory = sessionFactory;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addCustomer(Customer customer) {
        customer.setCustomerPassword(passwordEncoder.encode(customer.getCustomerPassword()));
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
    public void delete(Customer customer) {
            sessionFactory.getCurrentSession().delete(customer);
    }

    @Override
    public void update(Customer customer) {
            customer.setCustomerPassword(passwordEncoder.encode(customer.getCustomerPassword()));
            sessionFactory.getCurrentSession().update(customer);
    }
}

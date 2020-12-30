package com.javaschool.onlineshop.model.dao.impl;


import com.javaschool.onlineshop.model.dao.CustomerDAO;
import com.javaschool.onlineshop.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    private final SessionFactory sessionFactory;

    private BCryptPasswordEncoder passwordEncoder;

    public CustomerDAOImpl(SessionFactory sessionFactory, BCryptPasswordEncoder passwordEncoder) {
        this.sessionFactory = sessionFactory;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean addCustomer(Customer customer) {
        customer.setCustomerPassword(passwordEncoder.encode(customer.getCustomerPassword()));
        sessionFactory.getCurrentSession().persist(customer);
        return true;
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
    public Customer getByEmail(String email) {

        String query = "FROM Customer WHERE customerEmailAddress = : email";
        try {
            return sessionFactory.getCurrentSession().createQuery(query, Customer.class)
                    .setParameter("email", email).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean delete(Customer customer) {
        return false;
    }

    @Override
    public boolean update(Customer customer) {
        return false;
    }
}

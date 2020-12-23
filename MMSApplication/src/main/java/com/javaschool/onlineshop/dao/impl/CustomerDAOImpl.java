package com.javaschool.onlineshop.dao.impl;

import com.javaschool.onlineshop.dao.CustomerDAO;
import com.javaschool.onlineshop.entity.Customer;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    SessionFactory sessionFactory;

    public CustomerDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean addCustomer(Customer customer) {
        customer.setCustomerPassword("password");
        sessionFactory.getCurrentSession().persist(customer);
        return true;
    }

    @Override
    public Customer get(int id) {
        return null;
    }

    @Override
    public Customer get(String email) {
        return null;
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

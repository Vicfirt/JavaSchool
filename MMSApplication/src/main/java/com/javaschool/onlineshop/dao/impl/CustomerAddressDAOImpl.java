package com.javaschool.onlineshop.dao.impl;

import com.javaschool.onlineshop.dao.CustomerAddressDAO;
import com.javaschool.onlineshop.model.entity.CustomerAddress;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerAddressDAOImpl implements CustomerAddressDAO {

    private final SessionFactory sessionFactory;

    public CustomerAddressDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addAddress(CustomerAddress customerAddress) {
        sessionFactory.getCurrentSession().persist(customerAddress);
    }

    @Override
    public void updateAddress(CustomerAddress customerAddress) {
        sessionFactory.getCurrentSession().update(customerAddress);

    }

    @Override
    public void deleteAddress(CustomerAddress customerAddress) {
        sessionFactory.getCurrentSession().delete(customerAddress);
    }
}

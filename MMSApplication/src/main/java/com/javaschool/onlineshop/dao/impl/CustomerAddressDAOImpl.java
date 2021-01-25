package com.javaschool.onlineshop.dao.impl;

import com.javaschool.onlineshop.dao.CustomerAddressDAO;
import com.javaschool.onlineshop.model.entity.CustomerAddress;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 * This class is responsible for getting data from customer address database entity.
 */
@Repository
public class CustomerAddressDAOImpl implements CustomerAddressDAO {

    private final SessionFactory sessionFactory;

    public CustomerAddressDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public CustomerAddress addAddress(CustomerAddress customerAddress) {
        sessionFactory.getCurrentSession().persist(customerAddress);
        return customerAddress;
    }

    @Override
    public CustomerAddress updateAddress(CustomerAddress customerAddress) {
        sessionFactory.getCurrentSession().update(customerAddress);
        return customerAddress;
    }
}

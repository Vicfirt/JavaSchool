package com.javaschool.onlineshop.dao;


import com.javaschool.onlineshop.model.entity.Customer;

public interface CustomerDAO {

    boolean addCustomer(Customer customer);

    Customer get(Long id);

    Customer getByUsername(String username);

    boolean delete(Customer customer);

    boolean update(Customer customer);
}

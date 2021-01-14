package com.javaschool.onlineshop.dao;


import com.javaschool.onlineshop.model.entity.Customer;

public interface CustomerDAO {

    void addCustomer(Customer customer);

    Customer get(Long id);

    Customer getByUsername(String username);

    void update(Customer customer);
}

package com.javaschool.onlineshop.dao;


import com.javaschool.onlineshop.entity.Customer;

public interface CustomerDAO {

    boolean addCustomer(Customer customer);

    Customer get(Long id);

    Customer get(String email);

    boolean delete(Customer customer);

    boolean update(Customer customer);
}

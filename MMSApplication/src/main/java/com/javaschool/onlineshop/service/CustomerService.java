package com.javaschool.onlineshop.service;


import com.javaschool.onlineshop.model.dto.CustomerDTO;

public interface CustomerService {

    CustomerDTO getCustomer();

    void addCustomer(CustomerDTO customerDTO);

    CustomerDTO getByEmail(String email);
}

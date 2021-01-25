package com.javaschool.onlineshop.service;


import com.javaschool.onlineshop.model.dto.CustomerDTO;

import java.security.Principal;

public interface CustomerService {

    CustomerDTO getCustomer();

    CustomerDTO addCustomer(CustomerDTO customer);

    CustomerDTO getByUsername(String username);

    void updateCustomer(CustomerDTO customerDTO, Principal principal);

    CustomerDTO getById(Long id);
}

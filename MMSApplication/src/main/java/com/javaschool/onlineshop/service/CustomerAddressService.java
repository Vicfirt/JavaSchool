package com.javaschool.onlineshop.service;

import com.javaschool.onlineshop.model.dto.CustomerAddressDTO;
import com.javaschool.onlineshop.model.dto.CustomerDTO;

public interface CustomerAddressService {

    void addCustomerAddress(CustomerAddressDTO customerAddressDTO, CustomerDTO customerDTO);
}

package com.javaschool.onlineshop.dto.mapppers;

import com.javaschool.onlineshop.dto.CustomerDTO;
import com.javaschool.onlineshop.entity.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}

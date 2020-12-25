package com.javaschool.onlineshop.dto.mapppers;

import com.javaschool.onlineshop.dto.CustomerAddressDTO;
import com.javaschool.onlineshop.dto.CustomerDTO;
import com.javaschool.onlineshop.entity.Customer;
import com.javaschool.onlineshop.entity.CustomerAddress;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerAddressMapper {

    CustomerAddressDTO customerAddressToCustomerAddressDTO(CustomerAddress customerAddress);

    CustomerAddress customerAddressDTOToCustomerAddress(CustomerAddressDTO customerAddressDTO);

    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}

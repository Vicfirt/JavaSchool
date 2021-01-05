package com.javaschool.onlineshop.service.impl;

import com.javaschool.onlineshop.dao.CustomerAddressDAO;
import com.javaschool.onlineshop.mappers.CustomerAddressMapper;
import com.javaschool.onlineshop.model.dto.CustomerAddressDTO;
import com.javaschool.onlineshop.model.dto.CustomerDTO;
import com.javaschool.onlineshop.service.CustomerAddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerAddressServiceImpl implements CustomerAddressService {

    private final CustomerAddressDAO customerAddressDAO;

    private final CustomerAddressMapper customerAddressMapper;

    public CustomerAddressServiceImpl(CustomerAddressDAO customerAddressDAO, CustomerAddressMapper customerAddressMapper) {
        this.customerAddressDAO = customerAddressDAO;
        this.customerAddressMapper = customerAddressMapper;
    }

    @Override
    @Transactional
    public void addCustomerAddress(CustomerAddressDTO customerAddressDTO, CustomerDTO customerDTO) {
        CustomerAddressDTO address = customerDTO.getCustomerAddress();
        address.setBuilding(customerAddressDTO.getBuilding());
        address.setCity(customerAddressDTO.getCity());
        address.setCountry(customerAddressDTO.getCountry());
        address.setPostcode(customerAddressDTO.getPostcode());
        address.setStreet(customerAddressDTO.getStreet());
        address.setRoom(customerAddressDTO.getRoom());
        customerAddressDAO.updateAddress(customerAddressMapper
                .customerAddressDTOToCustomerAddress(address));
    }
}

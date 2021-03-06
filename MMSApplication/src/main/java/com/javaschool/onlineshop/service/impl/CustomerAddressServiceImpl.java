package com.javaschool.onlineshop.service.impl;

import com.javaschool.onlineshop.dao.CustomerAddressDAO;
import com.javaschool.onlineshop.mappers.CustomerAddressMapper;
import com.javaschool.onlineshop.model.dto.CustomerAddressDTO;
import com.javaschool.onlineshop.model.dto.CustomerDTO;
import com.javaschool.onlineshop.service.CustomerAddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class is responsible for processing data received from customer address DAO as well as preparing it for sending to the UI.
 */
@Service
public class CustomerAddressServiceImpl implements CustomerAddressService {

    private final CustomerAddressDAO customerAddressDAO;

    private final CustomerAddressMapper customerAddressMapper;

    public CustomerAddressServiceImpl(CustomerAddressDAO customerAddressDAO, CustomerAddressMapper customerAddressMapper) {
        this.customerAddressDAO = customerAddressDAO;
        this.customerAddressMapper = customerAddressMapper;
    }

    /**
     * This method is responsible for adding the address to the customer profile.
     * Also it calls DAO method to save the address.
     * @param customerAddressDTO            customer address to be added
     * @param customerDTO                   customer to whom the address is added
     */
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

    @Override
    @Transactional
    public void updateCustomerAddress(CustomerAddressDTO customerAddressDTO) {
        customerAddressDAO.updateAddress(customerAddressMapper
                .customerAddressDTOToCustomerAddress(customerAddressDTO));
    }
}

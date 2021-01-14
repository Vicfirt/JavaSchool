package com.javaschool.onlineshop.service.impl;

import com.javaschool.onlineshop.dao.CustomerAddressDAO;
import com.javaschool.onlineshop.model.entity.Cart;
import com.javaschool.onlineshop.model.entity.Customer;
import com.javaschool.onlineshop.mappers.CustomerMapper;
import com.javaschool.onlineshop.dao.CartDAO;
import com.javaschool.onlineshop.dao.CustomerDAO;
import com.javaschool.onlineshop.model.dto.CustomerDTO;
import com.javaschool.onlineshop.model.entity.CustomerAddress;
import com.javaschool.onlineshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDAO customerDAO;

    private final CustomerMapper customerMapper;

    private final CartDAO cartDAO;

    private final CustomerAddressDAO customerAddressDAO;

    @Autowired
    @Qualifier(value = "passwordEncoder")
    private BCryptPasswordEncoder passwordEncoder;

    public CustomerServiceImpl(CustomerDAO customerDAO, CustomerMapper customerMapper, CartDAO cartDAO, CustomerAddressDAO customerAddressDAO) {
        this.customerDAO = customerDAO;
        this.customerMapper = customerMapper;
        this.cartDAO = cartDAO;
        this.customerAddressDAO = customerAddressDAO;
    }

    @Override
    @Transactional
    public CustomerDTO getCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerDAO.getByUsername(authentication.getName());
        return customerMapper.customerToCustomerDTO(customer);
    }

    @Override
    @Transactional
    public void addCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        Cart cart = new Cart();
        CustomerAddress customerAddress = new CustomerAddress();
        cart.setCartTotal(0.0);
        cart.setElementsInCart(0);
        customer.setCart(cart);
        customer.setCustomerAddress(customerAddress);
        customer.setCustomerPassword(passwordEncoder.encode(customer.getCustomerPassword()));
        customerAddressDAO.addAddress(customerAddress);
        cartDAO.addCart(cart);
        customerDAO.addCustomer(customer);
    }

    @Override
    @Transactional
    public CustomerDTO getByUsername(String username) {
        Customer customer = customerDAO.getByUsername(username);
        return customerMapper.customerToCustomerDTO(customer);
    }

    @Override
    @Transactional
    public void update(CustomerDTO customerDTO, Principal principal) {
        Customer oldCustomer = customerDAO.getByUsername(principal.getName());
        oldCustomer.setCustomerFirstName(customerDTO.getCustomerFirstName());
        oldCustomer.setCustomerLastName(customerDTO.getCustomerLastName());
        oldCustomer.setCustomerEmailAddress(customerDTO.getCustomerEmailAddress());
        oldCustomer.setCustomerPassword(customerDTO.getCustomerPassword());
        oldCustomer.setCustomerPassword(passwordEncoder.encode(customerDTO.getCustomerPassword()));
        customerDAO.addCustomer(oldCustomer);
    }

    @Override
    @Transactional
    public CustomerDTO getById(Long id) {
        Customer customer = customerDAO.get(id);
        return customerMapper.customerToCustomerDTO(customer);
    }
}

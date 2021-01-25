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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDAO customerDAO;

    private final CustomerMapper customerMapper;

    private final CartDAO cartDAO;

    private final CustomerAddressDAO customerAddressDAO;

    private final PasswordEncoder passwordEncoder;

    public CustomerServiceImpl(CustomerDAO customerDAO, CustomerMapper customerMapper, CartDAO cartDAO, CustomerAddressDAO customerAddressDAO, BCryptPasswordEncoder passwordEncoder) {
        this.customerDAO = customerDAO;
        this.customerMapper = customerMapper;
        this.cartDAO = cartDAO;
        this.customerAddressDAO = customerAddressDAO;
        this.passwordEncoder = passwordEncoder;
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
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
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
        return customerDTO;
    }

    @Override
    @Transactional
    public CustomerDTO getByUsername(String username) {
        Customer customer = customerDAO.getByUsername(username);
        return customerMapper.customerToCustomerDTO(customer);
    }

    @Override
    @Transactional
    public void updateCustomer(CustomerDTO customerDTO, Principal principal) {
        Customer customer = customerDAO.getByUsername(principal.getName());
        customer.setCustomerFirstName(customerDTO.getCustomerFirstName());
        customer.setCustomerLastName(customerDTO.getCustomerLastName());
        customer.setCustomerEmailAddress(customerDTO.getCustomerEmailAddress());
        customer.setCustomerPassword(customerDTO.getCustomerPassword());
        customer.setCustomerPassword(passwordEncoder.encode(customerDTO.getCustomerPassword()));
        customerDAO.addCustomer(customer);
    }

    @Override
    @Transactional
    public CustomerDTO getById(Long id) {
        Customer customer = customerDAO.get(id);
        return customerMapper.customerToCustomerDTO(customer);
    }
}

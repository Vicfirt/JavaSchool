package com.javaschool.onlineshop.service.impl;

import com.javaschool.onlineshop.entity.Cart;
import com.javaschool.onlineshop.entity.Customer;
import com.javaschool.onlineshop.mappers.CustomerMapper;
import com.javaschool.onlineshop.model.dao.CartDAO;
import com.javaschool.onlineshop.model.dao.CustomerDAO;
import com.javaschool.onlineshop.model.dto.CustomerDTO;
import com.javaschool.onlineshop.service.CustomerService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerDAO customerDAO;

    CustomerMapper customerMapper;

    CartDAO cartDAO;

    public CustomerServiceImpl(CustomerDAO customerDAO, CustomerMapper customerMapper, CartDAO cartDAO) {
        this.customerDAO = customerDAO;
        this.customerMapper = customerMapper;
        this.cartDAO = cartDAO;
    }

    @Override
    @Transactional
    public CustomerDTO getCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Customer customer =  customerDAO.getByEmail(authentication.getName());
        return customerMapper.customerToCustomerDTO(customer);
    }

    @Override
    @Transactional
    public void addCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        Cart cart = new Cart();
        cart.setCartTotal(0.0);
        cart.setElementsInCart(0);
        cart.setCustomer(customer);
        cartDAO.addCart(cart);
        customerDAO.addCustomer(customer);
    }

    @Override
    @Transactional
    public CustomerDTO getByEmail(String email) {
        Customer customer = customerDAO.getByEmail(email);
        return customerMapper.customerToCustomerDTO(customer);
    }
}

package com.javaschool.onlineshop.service.impl;

import com.javaschool.onlineshop.dao.CartDAO;
import com.javaschool.onlineshop.dao.CustomerAddressDAO;
import com.javaschool.onlineshop.dao.CustomerDAO;
import com.javaschool.onlineshop.mappers.CustomerMapper;
import com.javaschool.onlineshop.model.dto.CustomerDTO;
import com.javaschool.onlineshop.model.entity.Cart;
import com.javaschool.onlineshop.model.entity.Customer;
import com.javaschool.onlineshop.model.entity.CustomerAddress;
import com.javaschool.onlineshop.service.CustomerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private CustomerAddressDAO customerAddressDAO;

    @Mock
    private CartDAO cartDAO;

    @Mock
    private CustomerDAO customerDAO;

    @Mock
    private Customer customer;

    @Mock
    private CustomerDTO customerDTO;

    @Mock
    private Cart cart;

    @Mock
    private CustomerAddress customerAddress;

    @InjectMocks
    CustomerServiceImpl customerService;

    @Test
    public void addCustomer() {
        Mockito.when(customerMapper.customerDTOToCustomer(customerDTO)).thenReturn(customer);
        Mockito.when(passwordEncoder.encode(customer.getCustomerPassword())).thenReturn("password");
        Assert.assertNotNull(cart.getCartTotal());
        Assert.assertNotNull(customer.getCart());

    }
}
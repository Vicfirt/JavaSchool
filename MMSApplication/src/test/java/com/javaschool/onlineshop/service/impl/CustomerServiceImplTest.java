
package com.javaschool.onlineshop.service.impl;

import com.javaschool.onlineshop.dao.CartDAO;
import com.javaschool.onlineshop.dao.CustomerAddressDAO;
import com.javaschool.onlineshop.dao.CustomerDAO;
import com.javaschool.onlineshop.mappers.CustomerMapper;
import com.javaschool.onlineshop.model.dto.CustomerDTO;
import com.javaschool.onlineshop.model.entity.Cart;
import com.javaschool.onlineshop.model.entity.Customer;
import com.javaschool.onlineshop.model.entity.CustomerAddress;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;


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

    @InjectMocks
    @Spy
    CustomerServiceImpl customerService;

    //TODO: probably use factory to create objects of tested class.
    @Test
    public void addCustomer() {
        Customer customer = new Customer();
        Cart cart = new Cart();
        CustomerAddress customerAddress = new CustomerAddress();
        CustomerDTO customerDTO = new CustomerDTO();
        Mockito.when(customerMapper.customerDTOToCustomer(customerDTO)).thenReturn(customer);
        customer.setCustomerPassword("password");
        Mockito.when(passwordEncoder.encode(customer.getCustomerPassword())).thenReturn("encryptedPassword");
        Mockito.when(customerAddressDAO.addAddress(customerAddress)).thenReturn(customerAddress);
        Mockito.when(cartDAO.addCart(cart)).thenReturn(cart);
        customerService.addCustomer(customerDTO);
        Assert.assertEquals((Double) 0.0, cart.getCartTotal());
    }
}




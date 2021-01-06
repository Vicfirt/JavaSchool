package com.javaschool.onlineshop.config;

import com.javaschool.onlineshop.dao.CustomerDAO;
import com.javaschool.onlineshop.model.entity.Customer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final CustomerDAO customerDAO;

    public MyUserDetailsService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Customer customer = customerDAO.getByUsername(username);
        if (customer == null){
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(customer);
    }
}

package com.javaschool.onlineshop.dto.mapppers;


import com.javaschool.onlineshop.dto.CartDTO;
import com.javaschool.onlineshop.dto.CustomerDTO;
import com.javaschool.onlineshop.entity.Cart;
import com.javaschool.onlineshop.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartDTO cartToDTO(Cart cart);

    Cart cartDTOToCart(CartDTO cartDTO);

    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDTOTOCustomer(CustomerDTO customerDTO);
}

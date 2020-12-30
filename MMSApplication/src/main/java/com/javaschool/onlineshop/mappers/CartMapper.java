package com.javaschool.onlineshop.mappers;


import com.javaschool.onlineshop.model.dto.CartDTO;
import com.javaschool.onlineshop.model.dto.CustomerDTO;
import com.javaschool.onlineshop.entity.Cart;
import com.javaschool.onlineshop.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartDTO cartToDTO(Cart cart);

    Cart cartDTOToCart(CartDTO cartDTO);

    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDTOToCustomer(CustomerDTO customerDTO);

}

package com.javaschool.onlineshop.dto.mapppers;


import com.javaschool.onlineshop.dto.CartDTO;
import com.javaschool.onlineshop.dto.CustomerAddressDTO;
import com.javaschool.onlineshop.dto.CustomerDTO;
import com.javaschool.onlineshop.entity.Cart;
import com.javaschool.onlineshop.entity.Customer;
import com.javaschool.onlineshop.entity.CustomerAddress;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDTOToCustomer(CustomerDTO customerDTO);

    CartDTO cartToCartDTO(Cart cart);

    Cart cartDTOToCart(CartDTO cartDTO);

    List<CustomerAddressDTO> addressListToAddressDTOList(List<CustomerAddress> customerAddressList);

    List<CustomerAddress> addressDTOListToAddressList(List<CustomerAddressDTO> customerAddressDTOList);
}

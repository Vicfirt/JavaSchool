package com.javaschool.onlineshop.mappers;


import com.javaschool.onlineshop.model.dto.CartDTO;
import com.javaschool.onlineshop.model.entity.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartDTO cartToDTO(Cart cart);

    Cart cartDTOToCart(CartDTO cartDTO);
}

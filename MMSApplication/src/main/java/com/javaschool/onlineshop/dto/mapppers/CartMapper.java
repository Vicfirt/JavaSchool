package com.javaschool.onlineshop.dto.mapppers;

import com.javaschool.onlineshop.dto.CartDTO;
import com.javaschool.onlineshop.entity.Cart;
import org.mapstruct.Mapper;

@Mapper
public interface CartMapper {

    CartDTO cartToDTO(Cart cart);

    Cart cartDTOToCart(CartDTO cartDTO);
}

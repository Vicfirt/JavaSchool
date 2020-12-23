package com.javaschool.onlineshop.dto.mapppers;

import com.javaschool.onlineshop.dto.CartElementDTO;
import com.javaschool.onlineshop.entity.CartElement;
import org.mapstruct.Mapper;

@Mapper
public interface CartElementMapper {

    CartElementDTO cartElementToCartElementDTO(CartElement cartElement);

    CartElement cartElementDTOToCartElement(CartElementDTO cartElementDTO);
}

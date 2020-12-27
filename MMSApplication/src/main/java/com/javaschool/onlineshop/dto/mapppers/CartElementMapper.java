package com.javaschool.onlineshop.dto.mapppers;


import com.javaschool.onlineshop.dto.CartElementDTO;
import com.javaschool.onlineshop.dto.ProductDTO;
import com.javaschool.onlineshop.entity.CartElement;
import com.javaschool.onlineshop.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartElementMapper {

    CartElementDTO cartElementToCartElementDTO(CartElement cartElement);

    CartElement cartElementDTOToCartElement(CartElementDTO cartElementDTO);

    ProductDTO productToDto(Product product);

    Product productDTOToProduct(ProductDTO productDTO);
}

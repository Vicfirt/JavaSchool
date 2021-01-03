package com.javaschool.onlineshop.mappers;


import com.javaschool.onlineshop.model.dto.CartElementDTO;
import com.javaschool.onlineshop.model.dto.ProductDTO;
import com.javaschool.onlineshop.model.entity.CartElement;
import com.javaschool.onlineshop.model.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartElementMapper {

    CartElementDTO cartElementToCartElementDTO(CartElement cartElement);

    CartElement cartElementDTOToCartElement(CartElementDTO cartElementDTO);

    ProductDTO productToDto(Product product);

    Product productDTOToProduct(ProductDTO productDTO);
}

package com.javaschool.onlineshop.mappers;

import com.javaschool.onlineshop.model.dto.OrderElementDTO;
import com.javaschool.onlineshop.model.dto.ProductDTO;
import com.javaschool.onlineshop.model.entity.OrderElement;
import com.javaschool.onlineshop.model.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderElementMapper {

    OrderElementDTO orderElementToOrderElementDTO(OrderElement orderElement);

    OrderElement orderElementDTOToOrderElement(OrderElementDTO orderElementDTO);

    ProductDTO productToProductDTO(Product product);

    Product productDTOToProduct(ProductDTO productDTO);

}

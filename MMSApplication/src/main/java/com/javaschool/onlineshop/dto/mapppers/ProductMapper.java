package com.javaschool.onlineshop.dto.mapppers;

import com.javaschool.onlineshop.dto.ProductDTO;
import com.javaschool.onlineshop.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO productToProductDTO(Product product);

    Product productDTOToProduct(ProductDTO productDTO);
}

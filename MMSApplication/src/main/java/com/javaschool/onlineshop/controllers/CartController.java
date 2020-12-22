package com.javaschool.onlineshop.controllers;

import com.javaschool.onlineshop.dto.CartDTO;
import com.javaschool.onlineshop.entity.CartElement;
import com.javaschool.onlineshop.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

public class CartController {

    CartService cartService;

    ModelMapper modelMapper;

    public CartController(CartService cartService, ModelMapper modelMapper) {
        this.cartService = cartService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    @ResponseBody
    public List<CartDTO> cartList() {

        List<CartElement> cartElements = cartService.cartList();
        return cartElements.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private CartDTO convertToDto(CartElement cartElement) {
        CartDTO cartDTO = modelMapper.map(cartElement, CartDTO.class);
        cartDTO.setTotalPrice(cartElement.getTotalPrice());
        cartDTO.setAvailable(cartElement.getAvailable());
        cartDTO.setBuyingPrice(cartElement.getBuyingPrice());
        cartDTO.setCart(cartElement.getCart());
        cartDTO.setProduct(cartElement.getProduct());
        cartDTO.setProductCount(cartElement.getProductCount());
        return cartDTO;
    }
}

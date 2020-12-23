package com.javaschool.onlineshop.dto;


import java.util.List;

public class CartDTO {

    private CustomerDTO customerDTO;

    private Double totalPrice;

    private Integer elementsInCart;

    private List<CartElementDTO> cartElementDTOS;
}

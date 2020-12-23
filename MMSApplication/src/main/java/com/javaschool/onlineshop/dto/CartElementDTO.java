package com.javaschool.onlineshop.dto;


import com.javaschool.onlineshop.entity.Product;


public class CartElementDTO {

    private Long id;

    private CartDTO cartDTO;

    private Product product;

    private Integer productCount;

    private Double totalPrice;

    private Double buyingPrice;

    private Boolean isAvailable = true;
}


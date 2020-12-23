package com.javaschool.onlineshop.dto;


import java.util.List;

public class CartDTO {

    private CustomerDTO customerDTO;

    private Double totalPrice;

    private Integer elementsInCart;

    private List<CartElementDTO> cartElementDTOS;

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getElementsInCart() {
        return elementsInCart;
    }

    public void setElementsInCart(Integer elementsInCart) {
        this.elementsInCart = elementsInCart;
    }

    public List<CartElementDTO> getCartElementDTOS() {
        return cartElementDTOS;
    }

    public void setCartElementDTOS(List<CartElementDTO> cartElementDTOS) {
        this.cartElementDTOS = cartElementDTOS;
    }
}

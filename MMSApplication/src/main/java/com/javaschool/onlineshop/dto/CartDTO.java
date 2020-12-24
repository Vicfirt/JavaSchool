package com.javaschool.onlineshop.dto;


public class CartDTO {

    private Long cartId;

    private CustomerDTO customerDTO;

    private Double cartTotal;

    private Integer elementsInCart;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public Double getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(Double cartTotal) {
        this.cartTotal = cartTotal;
    }

    public Integer getElementsInCart() {
        return elementsInCart;
    }

    public void setElementsInCart(Integer elementsInCart) {
        this.elementsInCart = elementsInCart;
    }
}

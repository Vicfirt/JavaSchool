package com.javaschool.onlineshop.dto;


public class OrderInfoDTO {

    private Long purchaseId;

    private CustomerDTO customerDTO;

    private CustomerAddressDTO customerAddressDTO;

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public CustomerAddressDTO getCustomerAddressDTO() {
        return customerAddressDTO;
    }

    public void setCustomerAddressDTO(CustomerAddressDTO customerAddressDTO) {
        this.customerAddressDTO = customerAddressDTO;
    }
}

package com.javaschool.onlineshop.dto;


import java.util.List;

public class CustomerDTO {

    private Long customerId;

    private List<OrderInfoDTO> orderInfoDTOList;

    private String customerFirstName;

    private String customerLastName;

    private String customerDateOfBirth;

    private String customerEmailAddress;

    private String customerPassword;

    private String role;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<OrderInfoDTO> getOrderInfoDTOList() {
        return orderInfoDTOList;
    }

    public void setOrderInfoDTOList(List<OrderInfoDTO> orderInfoDTOList) {
        this.orderInfoDTOList = orderInfoDTOList;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerDateOfBirth() {
        return customerDateOfBirth;
    }

    public void setCustomerDateOfBirth(String customerDateOfBirth) {
        this.customerDateOfBirth = customerDateOfBirth;
    }

    public String getCustomerEmailAddress() {
        return customerEmailAddress;
    }

    public void setCustomerEmailAddress(String customerEmailAddress) {
        this.customerEmailAddress = customerEmailAddress;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

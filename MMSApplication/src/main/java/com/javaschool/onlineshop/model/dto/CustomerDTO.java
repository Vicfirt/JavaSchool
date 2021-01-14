package com.javaschool.onlineshop.model.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CustomerDTO {

    private Long customerId;

    private CustomerAddressDTO customerAddress;

    @Size(min = 2, max = 16, message = "Last name must contain 2 to 16 characters.")
    private String customerFirstName;

    @Size(min = 2, max = 16, message = "Last name must contain 2 to 16 characters." )
    private String customerLastName;

    @NotEmpty
    private String customerDateOfBirth;

    @Email(message = "Please, enter correct email address.")
    private String customerEmailAddress;

    @Size(min = 6, max = 15, message = "A password must contain 6 to 15 characters.")
    private String customerPassword;

    private String role;

    private Boolean active;

    @NotEmpty
    @Size(min=7, max = 11, message = "Your phone number myst contain 7 to 11 numbers.")
    private String phoneNumber;

    private CartDTO cart;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public CartDTO getCart() {
        return cart;
    }

    public void setCart(CartDTO cart) {
        this.cart = cart;
    }

    public CustomerAddressDTO getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(CustomerAddressDTO customerAddress) {
        this.customerAddress = customerAddress;
    }
}
package com.javaschool.onlineshop.dto;


import java.util.List;

public class CustomerAddressDTO {

    private Long customerAddressId;

    private List<OrderInfoDTO> orderInfosDTOs;

    private String country;

    private String city;

    private String postcode;

    private String street;

    private String building;

    private String room;

    public Long getCustomerAddressId() {
        return customerAddressId;
    }

    public void setCustomerAddressId(Long customerAddressId) {
        this.customerAddressId = customerAddressId;
    }

    public List<OrderInfoDTO> getOrderInfosDTOs() {
        return orderInfosDTOs;
    }

    public void setOrderInfosDTOs(List<OrderInfoDTO> orderInfosDTOs) {
        this.orderInfosDTOs = orderInfosDTOs;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}

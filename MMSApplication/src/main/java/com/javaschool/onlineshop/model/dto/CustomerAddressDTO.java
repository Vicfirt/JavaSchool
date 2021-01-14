package com.javaschool.onlineshop.model.dto;


import javax.validation.constraints.Size;

public class CustomerAddressDTO {

    private Long customerAddressId;

    @Size(min = 2, max = 40, message = "The number of letters in the country name must be between 2 and 20")
    private String country;

    @Size(min = 2, max = 20, message = "The number of letters in the city name must be between 2 and 20")
    private String city;

    @Size(min = 4, max = 10, message = "The number of characters in the postcode must be be from 4 to 10")
    private String postcode;

    @Size(min = 2, max = 40, message = "The number of letters in the street name must be between 2 and 40")
    private String street;

    private String building;

    private String room;

    public Long getCustomerAddressId() {
        return customerAddressId;
    }

    public void setCustomerAddressId(Long customerAddressId) {
        this.customerAddressId = customerAddressId;
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

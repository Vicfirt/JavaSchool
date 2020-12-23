package com.javaschool.onlineshop.dto;

import com.javaschool.onlineshop.entity.OrderInfo;

import javax.persistence.*;
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
}

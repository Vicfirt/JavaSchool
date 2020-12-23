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
}

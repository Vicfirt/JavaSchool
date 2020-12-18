package com.javaSchool.onlineShop.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "order")
public class Order{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "customer")
    private String customer;

    @Column(name = "customer_address")
    private String customerAddress;

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }


    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
}


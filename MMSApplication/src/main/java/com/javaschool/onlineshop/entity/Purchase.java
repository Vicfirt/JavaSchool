package com.javaschool.onlineshop.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;


@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long purchaseId;

    @Column(name = "customer")
    private String customer;

    @Column(name = "customer_address")
    private String customerAddress;

    public long getId() {
        return purchaseId;
    }


    public void setId(long id) {
        this.purchaseId = id;
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


package com.javaschool.onlineshop.dao;

import com.javaschool.onlineshop.model.entity.CustomerAddress;

public interface CustomerAddressDAO {

    void addAddress(CustomerAddress customerAddress);

    void updateAddress(CustomerAddress customerAddress);

    void deleteAddress(CustomerAddress customerAddress);
}

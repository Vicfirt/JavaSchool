package com.javaschool.onlineshop.dao;

import com.javaschool.onlineshop.model.entity.CustomerAddress;

public interface CustomerAddressDAO {

    CustomerAddress addAddress(CustomerAddress customerAddress);

    CustomerAddress updateAddress(CustomerAddress customerAddress);
}

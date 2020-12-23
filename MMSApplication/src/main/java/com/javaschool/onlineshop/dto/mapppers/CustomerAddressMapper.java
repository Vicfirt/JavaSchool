package com.javaschool.onlineshop.dto.mapppers;

import com.javaschool.onlineshop.dto.CustomerAddressDTO;
import com.javaschool.onlineshop.entity.CustomerAddress;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerAddressMapper {

    CustomerAddressDTO customerAddressToCustomerAddressDTO(CustomerAddress customerAddress);

    CustomerAddress customerAddressDTOToCustomerAddress(CustomerAddressDTO customerAddressDTO);
}

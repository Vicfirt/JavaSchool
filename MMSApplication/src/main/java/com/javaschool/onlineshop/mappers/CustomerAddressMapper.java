package com.javaschool.onlineshop.mappers;


import com.javaschool.onlineshop.model.dto.CustomerAddressDTO;
import com.javaschool.onlineshop.model.entity.CustomerAddress;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerAddressMapper {

    CustomerAddressDTO customerAddressToCustomerAddressDTO(CustomerAddress customerAddress);

    CustomerAddress customerAddressDTOToCustomerAddress(CustomerAddressDTO customerAddressDTO);
}

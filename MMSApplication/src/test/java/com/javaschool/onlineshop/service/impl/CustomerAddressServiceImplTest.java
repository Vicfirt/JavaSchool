package com.javaschool.onlineshop.service.impl;

import com.javaschool.onlineshop.dao.CustomerAddressDAO;
import com.javaschool.onlineshop.mappers.CustomerAddressMapper;
import com.javaschool.onlineshop.model.dto.CustomerAddressDTO;
import com.javaschool.onlineshop.model.dto.CustomerDTO;
import com.javaschool.onlineshop.model.entity.CustomerAddress;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class CustomerAddressServiceImplTest {

    @Mock
    CustomerAddressDAO customerAddressDAO;

    @Mock
    CustomerAddressMapper customerAddressMapper;

    @Mock
    CustomerDTO customerDTO;

    @InjectMocks
    @Spy
    CustomerAddressServiceImpl customerAddressService;

    @Test
    public void addCustomerAddress() {
        CustomerAddressDTO customerAddressDTO = new CustomerAddressDTO();
        CustomerAddressDTO paramCustomerAddress = new CustomerAddressDTO();
        paramCustomerAddress.setPostcode("1908765");
        paramCustomerAddress.setCountry("Russia");
        paramCustomerAddress.setStreet("Popova");
        paramCustomerAddress.setRoom("12");
        paramCustomerAddress.setBuilding("87");
        paramCustomerAddress.setCity("Moscow");
        Mockito.when(customerDTO.getCustomerAddress()).thenReturn(customerAddressDTO);
        Mockito.when(customerAddressDAO.updateAddress(customerAddressMapper
                .customerAddressDTOToCustomerAddress(customerAddressDTO))).thenReturn(new CustomerAddress());
        customerAddressService.addCustomerAddress(paramCustomerAddress, customerDTO);
        Assert.assertEquals(paramCustomerAddress.getCity(), customerAddressDTO.getCity());
        Assert.assertEquals(paramCustomerAddress.getBuilding(), customerAddressDTO.getBuilding());
        Assert.assertEquals(paramCustomerAddress.getCountry(), customerAddressDTO.getCountry());
        Assert.assertEquals(paramCustomerAddress.getPostcode(), customerAddressDTO.getPostcode());
        Assert.assertEquals(paramCustomerAddress.getStreet(), customerAddressDTO.getStreet());
        Assert.assertEquals(paramCustomerAddress.getRoom(), customerAddressDTO.getRoom());
    }
}


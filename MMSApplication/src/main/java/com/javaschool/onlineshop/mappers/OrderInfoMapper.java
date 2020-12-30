package com.javaschool.onlineshop.mappers;


import com.javaschool.onlineshop.model.dto.OrderInfoDTO;
import com.javaschool.onlineshop.entity.OrderInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderInfoMapper {

    OrderInfoDTO orderInfoToOrderInfoDTO(OrderInfo orderInfo);

    OrderInfo orderInfoDTOToOrderInfo(OrderInfoDTO orderInfoDTO);
}

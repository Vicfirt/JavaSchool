package com.javaschool.onlineshop.service.impl;

import com.javaschool.onlineshop.dao.OrderDAO;
import com.javaschool.onlineshop.dao.CustomerDAO;
import com.javaschool.onlineshop.dao.CartElementDAO;
import com.javaschool.onlineshop.dao.OrderElementDAO;
import com.javaschool.onlineshop.dao.CartDAO;
import com.javaschool.onlineshop.model.dto.CartElementDTO;
import com.javaschool.onlineshop.model.dto.OrderInfoDTO;
import com.javaschool.onlineshop.mappers.CartElementMapper;
import com.javaschool.onlineshop.mappers.OrderInfoMapper;
import com.javaschool.onlineshop.model.entity.CartElement;
import com.javaschool.onlineshop.model.entity.OrderInfo;
import com.javaschool.onlineshop.model.entity.OrderElement;
import com.javaschool.onlineshop.model.entity.Customer;
import com.javaschool.onlineshop.model.entity.Cart;
import com.javaschool.onlineshop.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;

    private final CustomerDAO customerDAO;

    private final CartElementDAO cartElementDAO;

    private final OrderElementDAO orderElementDAO;

    private final CartDAO cartDAO;

    private final OrderInfoMapper orderInfoMapper;

    private final CartElementMapper cartElementMapper;

    public OrderServiceImpl(OrderDAO orderDAO, CustomerDAO customerDAO, CartElementDAO cartElementDAO, OrderElementDAO orderElementDAO, CartDAO cartDAO, OrderInfoMapper orderInfoMapper, CartElementMapper cartElementMapper) {
        this.orderDAO = orderDAO;
        this.customerDAO = customerDAO;
        this.cartElementDAO = cartElementDAO;
        this.orderElementDAO = orderElementDAO;
        this.cartDAO = cartDAO;
        this.orderInfoMapper = orderInfoMapper;
        this.cartElementMapper = cartElementMapper;
    }

    @Override
    @Transactional
    public List<OrderInfoDTO> findAllOrders(Long customerId) {
        List<OrderInfo> orders = orderDAO.findAllOrders(customerId);
        List<OrderInfoDTO> ordersDTOList = new ArrayList<>();
        for (OrderInfo orderInfo : orders) {
            ordersDTOList.add(orderInfoMapper.orderInfoToOrderInfoDTO(orderInfo));
        }
        return ordersDTOList;
    }

    @Override
    @Transactional
    public void addOrder(OrderInfoDTO orderInfoDTO, List<CartElementDTO> elementList) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OrderInfo orderInfo = orderInfoMapper.orderInfoDTOToOrderInfo(orderInfoDTO);
        orderDAO.add(orderInfo);
        Customer customer = customerDAO.getByUsername(authentication.getName());
        for (CartElementDTO cartElementDTO : elementList) {
            CartElement cartElement = cartElementMapper.cartElementDTOToCartElement(cartElementDTO);
            OrderElement element = new OrderElement();
            element.setOrderId(orderInfo.getOrderId());
            element.setPrice(cartElement.getElementPrice());
            element.setProduct(cartElement.getProduct());
            element.setTotalPrice(cartElement.getTotalPrice());
            element.setProductCount(cartElement.getProductCount());
            orderElementDAO.add(element);
            cartElementDAO.delete(cartElement.getId());
        }
        Cart customerCart = customer.getCart();
        customerCart.setElementsInCart(0);
        customerCart.setCartTotal(0.0);
        cartDAO.updateCart(customerCart);
    }
}

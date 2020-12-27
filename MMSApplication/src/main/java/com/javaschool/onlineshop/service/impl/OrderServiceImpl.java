package com.javaschool.onlineshop.service.impl;

import com.javaschool.onlineshop.dao.OrderDAO;
import com.javaschool.onlineshop.dao.CustomerDAO;
import com.javaschool.onlineshop.dao.CartElementDAO;
import com.javaschool.onlineshop.dao.OrderElementDAO;
import com.javaschool.onlineshop.dao.CartDAO;
import com.javaschool.onlineshop.dto.CartElementDTO;
import com.javaschool.onlineshop.dto.OrderInfoDTO;
import com.javaschool.onlineshop.dto.mapppers.CartElementMapper;
import com.javaschool.onlineshop.dto.mapppers.OrderInfoMapper;
import com.javaschool.onlineshop.entity.CartElement;
import com.javaschool.onlineshop.entity.OrderInfo;
import com.javaschool.onlineshop.entity.OrderElement;
import com.javaschool.onlineshop.entity.Customer;
import com.javaschool.onlineshop.entity.Cart;
import com.javaschool.onlineshop.service.OrderService;
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
    public void createOrder(List<CartElementDTO> elementList) {

        Customer customer = customerDAO.get(1L);
        OrderInfo order = new OrderInfo();
        order.setCustomerId(customer.getCustomerId());
        order.setTotal(customer.getCart().getCartTotal());
        order.setOrderCount(elementList.size());
        orderDAO.add(order);

        for (CartElementDTO cartElementDTO : elementList) {
            CartElement cartElement = cartElementMapper.cartElementDTOToCartElement(cartElementDTO);
            OrderElement element = new OrderElement();
            element.setOrderId(order.getOrderId());
            element.setPrice(cartElement.getElementPrice());
            element.setProduct(cartElement.getProduct());
            element.setTotalPrice(cartElement.getTotalPrice());
            element.setProductCount(cartElement.getProductCount());
            orderElementDAO.add(element);
            cartElementDAO.delete(cartElement.getId());
        }

        Cart customerCart = cartDAO.getCartById(1L);
        customerCart.setElementsInCart(0);
        customerCart.setCartTotal(0.0);
        cartDAO.updateCart(customerCart);
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
}

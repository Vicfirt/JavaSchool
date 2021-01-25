package com.javaschool.onlineshop.service.impl;

import com.javaschool.onlineshop.dao.OrderDAO;
import com.javaschool.onlineshop.dao.CartElementDAO;
import com.javaschool.onlineshop.dao.OrderElementDAO;
import com.javaschool.onlineshop.dao.CustomerDAO;
import com.javaschool.onlineshop.mappers.OrderElementMapper;
import com.javaschool.onlineshop.model.dto.CartElementDTO;
import com.javaschool.onlineshop.model.dto.CustomerDTO;
import com.javaschool.onlineshop.model.dto.OrderElementDTO;
import com.javaschool.onlineshop.model.dto.OrderInfoDTO;
import com.javaschool.onlineshop.mappers.CartElementMapper;
import com.javaschool.onlineshop.mappers.OrderInfoMapper;
import com.javaschool.onlineshop.model.entity.CartElement;
import com.javaschool.onlineshop.model.entity.OrderInfo;
import com.javaschool.onlineshop.model.entity.OrderElement;
import com.javaschool.onlineshop.model.entity.Customer;
import com.javaschool.onlineshop.model.entity.Cart;
import com.javaschool.onlineshop.service.CartService;
import com.javaschool.onlineshop.service.CustomerService;
import com.javaschool.onlineshop.service.OrderService;
import com.javaschool.onlineshop.service.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;

    private final CartElementDAO cartElementDAO;

    private final OrderElementDAO orderElementDAO;

    private final OrderInfoMapper orderInfoMapper;

    private final CartElementMapper cartElementMapper;

    private final CartService cartService;

    private final CustomerDAO customerDAO;

    private final CustomerService customerService;

    private final ProductService productService;

    private final OrderElementMapper orderElementMapper;

    public OrderServiceImpl(OrderDAO orderDAO, CartElementDAO cartElementDAO, OrderElementDAO orderElementDAO, OrderInfoMapper orderInfoMapper, CartElementMapper cartElementMapper, CartService cartService, CustomerDAO customerDAO, CustomerService customerService, ProductService productService, OrderElementMapper orderElementMapper) {
        this.orderDAO = orderDAO;
        this.cartElementDAO = cartElementDAO;
        this.orderElementDAO = orderElementDAO;
        this.orderInfoMapper = orderInfoMapper;
        this.cartElementMapper = cartElementMapper;
        this.cartService = cartService;
        this.customerDAO = customerDAO;
        this.customerService = customerService;
        this.productService = productService;
        this.orderElementMapper = orderElementMapper;
    }

    @Override
    @Transactional
    public List<OrderInfoDTO> findAllCustomerOrders() {
        CustomerDTO customer = customerService.getCustomer();
        List<OrderInfo> orders = orderDAO.findAllCustomerOrders(customer.getCustomerId());
        List<OrderInfoDTO> ordersDTOList = new ArrayList<>();
        for (OrderInfo orderInfo : orders) {
            ordersDTOList.add(orderInfoMapper.orderInfoToOrderInfoDTO(orderInfo));
        }
        return ordersDTOList;
    }

    @Override
    @Transactional
    public List<OrderInfoDTO> findAllOrders() {
        List<OrderInfo> orderInfoList = orderDAO.findAllOrders();
        List<OrderInfoDTO> orderInfoDTOList = new ArrayList<>();
        for (OrderInfo orderInfo : orderInfoList) {
            orderInfoDTOList.add(orderInfoMapper.orderInfoToOrderInfoDTO(orderInfo));
        }
        return orderInfoDTOList;
    }

    @Override
    @Transactional
    public void addOrder(OrderInfoDTO orderInfoDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OrderInfo orderInfo = orderInfoMapper.orderInfoDTOToOrderInfo(orderInfoDTO);
        orderInfo.setTotal(cartService.getCart().getCartTotal());
        orderDAO.add(orderInfo);
        Customer customer = customerDAO.getByUsername(authentication.getName());
        createOrderElement(orderInfo);
        Cart customerCart = customer.getCart();
        customerCart.setElementsInCart(0);
        customerCart.setCartTotal(0.0);
        cartService.updateCart(customerCart);
    }

    private void createOrderElement(OrderInfo orderInfo) {
        List<CartElementDTO> cartElementList = cartService.getCartElements();
        for (CartElementDTO cartElementDTO : cartElementList) {
            CartElement cartElement = cartElementMapper.cartElementDTOToCartElement(cartElementDTO);
            OrderElement element = new OrderElement();
            element.setOrderId(orderInfo.getOrderId());
            element.setPrice(cartElement.getElementPrice());
            element.setProduct(cartElement.getProduct());
            element.setTotalPrice(cartElement.getTotalPrice());
            element.setProductCount(cartElement.getProductCount());
            productService.decreaseAmount(
                    cartElement.getProduct().getProductId(),
                    cartElement.getProductCount());
            orderElementDAO.add(element);
            cartElementDAO.delete(cartElement.getId());
        }
    }

    @Override
    @Transactional
    public void updateOrder(OrderInfoDTO orderInfoDTO) {
        OrderInfo orderInfo = orderInfoMapper.orderInfoDTOToOrderInfo(orderInfoDTO);
        orderDAO.update(orderInfo);
    }

    @Override
    @Transactional
    public OrderInfoDTO get(Long orderId) {
        OrderInfo orderInfo = orderDAO.get(orderId);
        return orderInfoMapper.orderInfoToOrderInfoDTO(orderInfo);
    }

    @Override
    @Transactional
    public List<OrderElementDTO> getAllOrderElements(Long orderId) {
        List<OrderElement> orderElementList = orderElementDAO.getAllOrderElements(orderId);
        List<OrderElementDTO> orderElementDTOList = new ArrayList<>();
        for (OrderElement orderElement : orderElementList){
            orderElementDTOList.add(orderElementMapper.orderElementToOrderElementDTO(orderElement));
        }
        return orderElementDTOList;
    }

    @Override
    @Transactional
    public void deleteOrder(Long orderId){
        orderDAO.delete(orderId);
        orderElementDAO.deleteOrderElementsByOrder(orderId);
    }
}

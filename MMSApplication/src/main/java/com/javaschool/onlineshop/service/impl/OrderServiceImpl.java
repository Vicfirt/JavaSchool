package com.javaschool.onlineshop.service.impl;

import com.javaschool.onlineshop.dao.OrderDAO;
import com.javaschool.onlineshop.dao.CustomerDAO;
import com.javaschool.onlineshop.dao.CartElementDAO;
import com.javaschool.onlineshop.dao.OrderElementDAO;
import com.javaschool.onlineshop.dao.CartDAO;
import com.javaschool.onlineshop.entity.CartElement;
import com.javaschool.onlineshop.entity.OrderInfo;
import com.javaschool.onlineshop.entity.OrderElement;
import com.javaschool.onlineshop.entity.Customer;
import com.javaschool.onlineshop.entity.Cart;
import com.javaschool.onlineshop.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;

    private final CustomerDAO customerDAO;

    private final CartElementDAO cartElementDAO;

    private final OrderElementDAO orderElementDAO;

    private final CartDAO cartDAO;

    public OrderServiceImpl(OrderDAO orderDAO, CustomerDAO customerDAO, CartElementDAO cartElementDAO, OrderElementDAO orderElementDAO, CartDAO cartDAO) {
        this.orderDAO = orderDAO;
        this.customerDAO = customerDAO;
        this.cartElementDAO = cartElementDAO;
        this.orderElementDAO = orderElementDAO;
        this.cartDAO = cartDAO;
    }

    @Override
    public void createOrder(List<CartElement> elementList) {

        Customer customer = customerDAO.get(1L);
        OrderInfo order = new OrderInfo();
        order.setCustomerId(customer.getCustomerId());
        order.setTotal(customer.getCart().getCartTotal());
        order.setOrderCount(elementList.size());
        orderDAO.add(order);

        for (CartElement cartElement : elementList) {
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
    public List<OrderInfo> findAllOrders(Long customerId) {
        return orderDAO.findAllOrders(customerId);
    }
}

package com.nikitin.javaSchool.mapper;

import com.nikitin.javaSchool.entity.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {

        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setCustomer(resultSet.getString("customer"));
        order.setCustomerAddress(resultSet.getString("customer_address"));
        order.setPaymentMethod(resultSet.getString("payment_method"));
        order.setDeliveryMethod(resultSet.getString("delivery_method"));
        order.setProducts(resultSet.getString("products"));
        order.setPaymentStatus(resultSet.getString("payment_status"));
        order.setOrderStatus(resultSet.getString("order_status"));

        return order;

    }
}


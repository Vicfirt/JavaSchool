package com.nikitin.javaSchool.dao.daoImplementations;

import com.nikitin.javaSchool.dao.OrderDAO;
import com.nikitin.javaSchool.entity.Order;
import com.nikitin.javaSchool.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderDAOImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Order> findAll() {
        String sql = "SELECT * FROM `order`";
        return jdbcTemplate.query(sql,new OrderMapper());
    }

    @Override
    public void save(Order order) {

        String sql = "INSERT INTO `order` (customer, customer_address, payment_method, " +
                                        "delivery_method, products, payment_status, order_status)" +
                                        "VALUES (?,?,?,?,?,?,?) ";

        jdbcTemplate.update(sql, order.getCustomer(), order.getCustomerAddress(),
                            order.getPaymentMethod(), order.getDeliveryMethod(),
                            order.getProducts(), order.getPaymentStatus(),
                            order.getOrderStatus());
    }

    @Override
    public Order getOrderById(int id) {

        String sql = "SELECT * FROM `order` WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new OrderMapper(),id);
    }

    @Override
    public void delete(int id) {

        String sql = "DELETE from order WHERE id=?";
        jdbcTemplate.update(sql, id);

    }

    @Override
    public void update(Order order) {

        String sql = "UPDATE `order` SET customer=?,customer_address=?,payment_method=?," +
                                        "delivery_method=?,products=?,payment_status=?,order_status=?" +
                                        "WHERE id=?";

        jdbcTemplate.update(sql, order.getCustomer(), order.getCustomerAddress(),
                order.getPaymentMethod(), order.getDeliveryMethod(),
                order.getProducts(), order.getPaymentStatus(),
                order.getOrderStatus(), order.getId());

    }
}

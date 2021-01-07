package com.javaschool.onlineshop.dao;


import com.javaschool.onlineshop.model.entity.CartElement;

import java.util.List;

public interface CartElementDAO {

    CartElement get(Long id);

    void add(CartElement cartElement);

    void update(CartElement cartElement);

    void delete(Long id);

    List<CartElement> findAll(Long cartId);
}

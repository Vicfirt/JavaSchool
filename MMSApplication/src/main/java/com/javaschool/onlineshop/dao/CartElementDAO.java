package com.javaschool.onlineshop.dao;


import com.javaschool.onlineshop.model.entity.CartElement;

import java.util.List;

public interface CartElementDAO {

    CartElement get(Long id);

    CartElement add(CartElement cartElement);

    CartElement update(CartElement cartElement);

    CartElement delete(Long id);

    List<CartElement> findAll(Long cartId);
}

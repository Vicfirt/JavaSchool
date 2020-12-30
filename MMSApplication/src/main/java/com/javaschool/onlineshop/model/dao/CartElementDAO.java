package com.javaschool.onlineshop.model.dao;


import com.javaschool.onlineshop.entity.CartElement;
import com.javaschool.onlineshop.entity.Product;

import java.util.List;

public interface CartElementDAO {

    CartElement get(Long id);

    boolean add(CartElement cartElement);

    boolean update(CartElement cartElement);

    boolean delete(Long id);

    List<CartElement> findAll(Long cartId);

    List<CartElement> listAvailable(Long cartId);

    CartElement get(Long cartId, Product product);

}

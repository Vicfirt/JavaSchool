package com.javaschool.onlineshop.dao;


import com.javaschool.onlineshop.model.entity.CartElement;
import com.javaschool.onlineshop.model.entity.Product;

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

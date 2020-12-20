package com.javaschool.onlineshop.service.impl;

import com.javaschool.onlineshop.dao.CartElementDAO;
import com.javaschool.onlineshop.entity.Cart;
import com.javaschool.onlineshop.entity.CartElement;
import com.javaschool.onlineshop.service.CartElementService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CartElementServiceImpl implements CartElementService {

    CartElementDAO cartElementDAO;

    public CartElementServiceImpl(CartElementDAO cartElementDAO) {
        this.cartElementDAO = cartElementDAO;
    }

    @Override
    @Transactional
    public CartElement getCart(int id) {
        return cartElementDAO.getCart(id);
    }

    @Override
    @Transactional
    public boolean add(CartElement cartElement) {
        cartElementDAO.add(cartElement);
        return true;
    }

    @Override
    @Transactional
    public boolean update(CartElement cartElement) {
        cartElementDAO.update(cartElement);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(CartElement cartElement) {
        cartElementDAO.delete(cartElement);
        return true;
    }

    @Override
    @Transactional
    public List<CartElement> cartList(int cartId) {
        return cartElementDAO.cartList(cartId);
    }

    @Override
    public boolean updateCart(Cart cart) {
        cartElementDAO.updateCart(cart);
        return true;
    }
}

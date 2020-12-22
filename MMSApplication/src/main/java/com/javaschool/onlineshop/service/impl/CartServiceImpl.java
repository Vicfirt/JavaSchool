package com.javaschool.onlineshop.service.impl;

import com.javaschool.onlineshop.dao.CartDAO;
import com.javaschool.onlineshop.entity.Cart;
import com.javaschool.onlineshop.entity.CartElement;
import com.javaschool.onlineshop.service.CartService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CartServiceImpl implements CartService {

    CartDAO cartDAO;

    public CartServiceImpl(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    @Override
    @Transactional
    public CartElement getCartElement(int id) {
        return cartDAO.getCartElement(id);
    }

    @Override
    @Transactional
    public boolean add(CartElement cartElement) {
        cartDAO.add(cartElement);
        return true;
    }

    @Override
    @Transactional
    public boolean update(CartElement cartElement) {
        cartDAO.update(cartElement);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(CartElement cartElement) {
        cartDAO.delete(cartElement);
        return true;
    }

    @Override
    @Transactional
    public List<CartElement> cartList() {
        return cartDAO.cartList();
    }

    @Override
    public boolean updateCart(Cart cart) {
        cartDAO.updateCart(cart);
        return true;
    }
}

package com.javaschool.onlineshop.service.impl;

import com.javaschool.onlineshop.dao.CartDAO;
import com.javaschool.onlineshop.dto.CartElementDTO;
import com.javaschool.onlineshop.dto.mapppers.CartElementMapper;
import com.javaschool.onlineshop.entity.Cart;
import com.javaschool.onlineshop.entity.CartElement;
import com.javaschool.onlineshop.service.CartService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    CartDAO cartDAO;

    public CartServiceImpl(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    @Override
    @Transactional
    public CartElementDTO getCartElement(int id) {
        CartElement cartElement = cartDAO.getCartElement(id);
        CartElementMapper mapper = Mappers.getMapper(CartElementMapper.class);
        return mapper.cartElementToCartElementDTO(cartElement);
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
    public List<CartElementDTO> cartList() {
        CartElementMapper mapper = Mappers.getMapper(CartElementMapper.class);
        List<CartElementDTO> cartElementDTOList = new ArrayList<>();
        List<CartElement> cartElementList = cartDAO.cartList();
        for (CartElement cartElement : cartElementList) {
            cartElementDTOList.add(mapper.cartElementToCartElementDTO(cartElement));
        }
        return cartElementDTOList;
    }

    @Override
    public boolean updateCart(Cart cart) {
        cartDAO.updateCart(cart);
        return true;
    }
}

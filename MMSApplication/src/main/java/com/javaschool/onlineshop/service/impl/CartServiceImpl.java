package com.javaschool.onlineshop.service.impl;

import com.javaschool.onlineshop.dao.CartDAO;
import com.javaschool.onlineshop.dao.CartElementDAO;
import com.javaschool.onlineshop.dao.CustomerDAO;
import com.javaschool.onlineshop.dto.CartElementDTO;
import com.javaschool.onlineshop.dto.mapppers.CartElementMapper;
import com.javaschool.onlineshop.dto.mapppers.CartMapper;
import com.javaschool.onlineshop.entity.Cart;
import com.javaschool.onlineshop.entity.CartElement;
import com.javaschool.onlineshop.entity.Product;
import com.javaschool.onlineshop.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartElementDAO cartElementDAO;

    private final CustomerDAO customerDAO;

    private final CartDAO cartDAO;

    private final CartMapper cartMapper;

    private final CartElementMapper cartElementMapper;

    public CartServiceImpl(CartElementDAO cartElementDAO, CustomerDAO customerDAO, CartDAO cartDAO, CartMapper cartMapper, CartElementMapper cartElementMapper) {
        this.cartElementDAO = cartElementDAO;
        this.customerDAO = customerDAO;
        this.cartDAO = cartDAO;
        this.cartMapper = cartMapper;
        this.cartElementMapper = cartElementMapper;
    }

    @Transactional
    public Cart getCart() {
        return cartDAO.getCartById(1L);
    }

    @Override
    @Transactional
    public void addCartElement(Product product) {
        Cart cart = cartDAO.getCartById(1L);
        cart.setElementsInCart(cart.getElementsInCart() + 1);
        CartElement cartElement = new CartElement();
        cartElement.setProduct(product);
        cartElement.setAvailable(product.isActive());
        cartElement.setCartId(cart.getCartId());
        cartElement.setProductCount(1);
        cartElement.setElementPrice(product.getProductPrice());
        cartElement.setTotalPrice(cartElement.getProductCount() * product.getProductPrice());
        cartElementDAO.add(cartElement);
        cart.setCartTotal(this.countTotal());
        cartDAO.updateCart(cart);
    }

    @Override
    @Transactional
    public void update(CartElement cartElement) {
        cartElementDAO.update(cartElement);
    }

    @Override
    @Transactional
    public void delete(CartElement cartElement) {
        Cart cart = this.getCart();
        cart.setElementsInCart(cart.getElementsInCart() - 1);
        cartElementDAO.delete(cartElement.getId());
        cart.setCartTotal(this.countTotal());
        cartDAO.updateCart(cart);
    }

    @Transactional
    public Double countTotal() {
        List<CartElementDTO> cartElementsDTOList = this.getCartElements();
        double total = 0;
        for (CartElementDTO element : cartElementsDTOList) {
            double elementTotal = element.getProductCount() * element.getElementPrice();
            total += elementTotal;
        }
        return total;
    }

    @Transactional
    public List<CartElementDTO> getCartElements() {
        List<CartElementDTO> cartElementDTOList = new ArrayList<>();
        Cart cart = this.getCart();
        List<CartElement> cartElementList = cartElementDAO.listAll(cart.getCartId());
        for (CartElement element : cartElementList) {
            cartElementDTOList.add(cartElementMapper.cartElementToCartElementDTO(element));
        }
        return cartElementDTOList;
    }
}

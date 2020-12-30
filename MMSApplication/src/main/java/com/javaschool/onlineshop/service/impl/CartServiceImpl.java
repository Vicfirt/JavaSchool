package com.javaschool.onlineshop.service.impl;


import com.javaschool.onlineshop.entity.Customer;
import com.javaschool.onlineshop.model.dao.CartDAO;
import com.javaschool.onlineshop.model.dao.CartElementDAO;
import com.javaschool.onlineshop.model.dao.CustomerDAO;
import com.javaschool.onlineshop.model.dto.CartElementDTO;
import com.javaschool.onlineshop.model.dto.ProductDTO;
import com.javaschool.onlineshop.mappers.CartElementMapper;
import com.javaschool.onlineshop.mappers.CartMapper;
import com.javaschool.onlineshop.mappers.ProductMapper;
import com.javaschool.onlineshop.entity.Cart;
import com.javaschool.onlineshop.entity.CartElement;
import com.javaschool.onlineshop.entity.Product;
import com.javaschool.onlineshop.service.CartService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private final ProductMapper productMapper;

    public CartServiceImpl(CartElementDAO cartElementDAO, CustomerDAO customerDAO, CartDAO cartDAO, CartMapper cartMapper, CartElementMapper cartElementMapper, ProductMapper productMapper) {
        this.cartElementDAO = cartElementDAO;
        this.customerDAO = customerDAO;
        this.cartDAO = cartDAO;
        this.cartMapper = cartMapper;
        this.cartElementMapper = cartElementMapper;
        this.productMapper = productMapper;
    }

    @Transactional
    public Cart getCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerDAO.getByEmail(authentication.getName());
        return customer.getCart();
    }

    @Override
    @Transactional
    public void addCartElement(ProductDTO productDTO) {
        Product product = productMapper.productDTOToProduct(productDTO);
        Cart cart = this.getCart();
        cart.setElementsInCart(cart.getElementsInCart() + 1);
        CartElement cartElement = new CartElement();
        cartElement.setProduct(product);
        cartElement.setAvailable(product.getActive());
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
    public void delete(Long cartElementId) {
        Cart cart = this.getCart();
        cart.setElementsInCart(cart.getElementsInCart() - 1);
        cartElementDAO.delete(cartElementId);
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
        List<CartElement> cartElementList = cartElementDAO.findAll(cart.getCartId());
        for (CartElement element : cartElementList) {
            cartElementDTOList.add(cartElementMapper.cartElementToCartElementDTO(element));
        }
        return cartElementDTOList;
    }

    @Transactional
    public CartElement getCartElementById(Long id) {
        return cartElementDAO.get(id);
    }

    @Transactional
    public void updateCartElement(Long cartElementId, Integer quantity) {
        CartElement cartElement = cartElementDAO.get(cartElementId);
        Cart cart = this.getCart();
        cartElement.setProductCount(quantity);
        cartElement.setTotalPrice(cartElement.getElementPrice() * quantity);
        cartElementDAO.update(cartElement);
        cart.setCartTotal(this.countTotal());
        cartDAO.updateCart(cart);
    }
}

package com.javaschool.onlineshop.service.impl;


import com.javaschool.onlineshop.model.entity.Customer;
import com.javaschool.onlineshop.dao.CartDAO;
import com.javaschool.onlineshop.dao.CartElementDAO;
import com.javaschool.onlineshop.dao.CustomerDAO;
import com.javaschool.onlineshop.model.dto.CartElementDTO;
import com.javaschool.onlineshop.model.dto.ProductDTO;
import com.javaschool.onlineshop.mappers.CartElementMapper;
import com.javaschool.onlineshop.mappers.ProductMapper;
import com.javaschool.onlineshop.model.entity.Cart;
import com.javaschool.onlineshop.model.entity.CartElement;
import com.javaschool.onlineshop.model.entity.Product;
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

    private final CartElementMapper cartElementMapper;

    private final ProductMapper productMapper;

    public CartServiceImpl(CartElementDAO cartElementDAO, CustomerDAO customerDAO, CartDAO cartDAO, CartElementMapper cartElementMapper, ProductMapper productMapper) {
        this.cartElementDAO = cartElementDAO;
        this.customerDAO = customerDAO;
        this.cartDAO = cartDAO;
        this.cartElementMapper = cartElementMapper;
        this.productMapper = productMapper;
    }

    @Transactional
    public Cart getCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerDAO.getByUsername(authentication.getName());
        return customer.getCart();
    }

    @Override
    @Transactional
    public void addCartElement(ProductDTO productDTO) {
        Cart cart = getCart();
        cart.setElementsInCart(cart.getElementsInCart() + 1);
        CartElement cartElement = createByProduct(productDTO);
        cartElement.setCartId(cart.getCartId());
        cartElementDAO.add(cartElement);
        cart.setCartTotal(countTotal());
        cartDAO.updateCart(cart);
    }

    @Override
    @Transactional
    public void updateCart(Cart cart) {
        cartDAO.updateCart(cart);
    }

    @Override
    @Transactional
    public void delete(Long cartElementId) {
        Cart cart = getCart();
        cart.setElementsInCart(cart.getElementsInCart() - 1);
        cartElementDAO.delete(cartElementId);
        cart.setCartTotal(countTotal());
        cartDAO.updateCart(cart);
    }

    @Transactional
    public Double countTotal() {
        List<CartElementDTO> cartElementsDTOList = getCartElements();
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
        Cart cart = getCart();
        List<CartElement> cartElementList = cartElementDAO.findAll(cart.getCartId());
        for (CartElement element : cartElementList) {
            cartElementDTOList.add(cartElementMapper.cartElementToCartElementDTO(element));
        }
        return cartElementDTOList;
    }

    @Transactional
    public void updateCartElement(Long cartElementId, Integer quantity) {
        CartElement cartElement = cartElementDAO.get(cartElementId);
        Cart cart = getCart();
        cartElement.setProductCount(quantity);
        cartElement.setTotalPrice(cartElement.getElementPrice() * quantity);
        cartElementDAO.update(cartElement);
        cart.setCartTotal(countTotal());
        cartDAO.updateCart(cart);
    }

    private CartElement createByProduct(ProductDTO productDTO) {
        Product product = productMapper.productDTOToProduct(productDTO);
        CartElement cartElement = new CartElement();
        cartElement.setProduct(product);
        cartElement.setAvailable(product.getActive());
        cartElement.setProductCount(1);
        cartElement.setElementPrice(product.getProductPrice());
        cartElement.setTotalPrice(cartElement.getProductCount() * product.getProductPrice());
        return cartElement;
    }
}

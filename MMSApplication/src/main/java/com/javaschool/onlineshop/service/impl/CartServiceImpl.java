package com.javaschool.onlineshop.service.impl;

import com.javaschool.onlineshop.dao.CartDAO;
import com.javaschool.onlineshop.dao.CartElementDAO;
import com.javaschool.onlineshop.dao.CustomerDAO;
import com.javaschool.onlineshop.entity.Cart;
import com.javaschool.onlineshop.entity.CartElement;
import com.javaschool.onlineshop.entity.Customer;
import com.javaschool.onlineshop.entity.Product;
import com.javaschool.onlineshop.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartElementDAO cartElementDAO;

    private final CustomerDAO customerDAO;

    private final CartDAO cartDAO;

    public CartServiceImpl(CartElementDAO cartElementDAO, CustomerDAO customerDAO, CartDAO cartDAO) {
        this.cartElementDAO = cartElementDAO;
        this.customerDAO = customerDAO;
        this.cartDAO = cartDAO;
    }

    @Transactional
    public Cart getCart() {
        Customer customer = customerDAO.get(1L);
        if (customer.getCart() == null){
            customer.setCart(new Cart());
            cartDAO.addCart(customer.getCart());
        }
        return customer.getCart();
    }

    @Override
    @Transactional
    public void addCartElement(Product product) {
        Cart cart = this.getCart();
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

        List<CartElement> cartElements = this.getCartElements();
        double total = 0;
        for (CartElement element : cartElements) {
            double elementTotal = element.getProductCount() * element.getElementPrice();
            total += elementTotal;
        }
        return total;
    }

    @Transactional
    public List<CartElement> getCartElements() {
        Cart cart = this.getCart();
        return cartElementDAO.listAll(cart.getCartId());
    }

}

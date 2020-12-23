package com.javaschool.onlineshop.service.impl;

import com.javaschool.onlineshop.dao.CartDAO;
import com.javaschool.onlineshop.dao.CustomerDAO;
import com.javaschool.onlineshop.dto.CartElementDTO;
import com.javaschool.onlineshop.dto.mapppers.CartElementMapper;
import com.javaschool.onlineshop.entity.Cart;
import com.javaschool.onlineshop.entity.CartElement;
import com.javaschool.onlineshop.entity.Customer;
import com.javaschool.onlineshop.entity.Product;
import com.javaschool.onlineshop.service.CartService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    CartDAO cartDAO;

    CustomerDAO customerDAO;

    public CartServiceImpl(CartDAO cartDAO, CustomerDAO customerDAO) {
        this.cartDAO = cartDAO;
        this.customerDAO = customerDAO;
    }

    @Transactional
    public Cart getCart() {
        Customer customer = customerDAO.get(1);
        return customer.getCart();
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
    public boolean addCartElement(Product product) {
        Cart cart = this.getCart();
        cart.setElementsInCart(cart.getElementsInCart() + 1);
        CartElement cartElement = new CartElement();
        cartElement.setProduct(product);
        cartElement.setAvailable(product.isActive());
        cartElement.setProductCount(1);
        cartElement.setElementPrice(product.getProductPrice());
        cartElement.setTotalPrice(cartElement.getProductCount() * product.getProductPrice());
        cartDAO.add(cartElement);
        cart.setCartTotal(this.countTotal());
        cartDAO.updateCart(cart);
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

    public Double countTotal() {

        List<CartElementDTO> cartElementsDTOS = this.cartList();
        double total = 0;

        for (CartElementDTO element : cartElementsDTOS) {
            double elementTotal = element.getProductCount() * element.getBuyingPrice();
            total += elementTotal;
        }
        return total;
    }
}

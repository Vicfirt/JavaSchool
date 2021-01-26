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

/**
 * This class is responsible for processing data received from cart DAO as well as preparing it for sending to the UI.
 */
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

    /**
     * This method returns the cart of the currently authorized user.
     * @return customer's cart
     */
    @Override
    @Transactional
    public Cart getCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerDAO.getByUsername(authentication.getName());
        return customer.getCart();
    }

    /**
     * This method is responsible for adding product to the cart. If product with this ID is already in cart,
     *  then it increases its quantity by 1. Otherwise a new cart element will be created and added to cart.
     * @param productDTO                    product to be added
     */
    @Override
    @Transactional
    public void addCartElement(ProductDTO productDTO) {
        Cart cart = getCart();
        Long checkResult = isProductInCart(productDTO.getProductId(), cart.getCartId());
        if (checkResult > 0){
            updateCartElement(checkResult, cart.getElementsInCart()+1);
        }
        else {
            cart.setElementsInCart(cart.getElementsInCart() + 1);
            CartElement cartElement = createByProduct(productDTO);
            cartElement.setCartId(cart.getCartId());
            cartElementDAO.add(cartElement);
            cart.setCartTotal(countTotal());
            cartDAO.updateCart(cart);
        }
    }

    @Override
    @Transactional
    public void updateCart(Cart cart) {
        cartDAO.updateCart(cart);
    }

    /**
     * This method removes the specified element from the cart and updates cart state.
     * @param cartElementId                 specifies cart element to be deleted
     */
    @Override
    @Transactional
    public void deleteCartElement(Long cartElementId) {
        Cart cart = getCart();
        cart.setElementsInCart(cart.getElementsInCart() - 1);
        cartElementDAO.delete(cartElementId);
        cart.setCartTotal(countTotal());
        cartDAO.updateCart(cart);
    }

    /**
     * This method calculates the total price of elements in the cart.
     * @return                              total price
     */
    @Override
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

    /**
     * This method returns a list of elements in the cart.
     * @return                          list of elements
     */
    @Override
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

    /**
     * This method updates the state of the specified cart element based on the received quantity of product.
     * @param cartElementId                 specifies the cart element to be updated
     * @param quantity                      quantity of products to be set to cart element
     */
    @Override
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

    /**
     * This method creates a new cart element based on the received product.
     * @param productDTO                    product to create cart element
     * @return                              created cart element
     */
    public CartElement createByProduct(ProductDTO productDTO) {
        Product product = productMapper.productDTOToProduct(productDTO);
        CartElement cartElement = new CartElement();
        cartElement.setProduct(product);
        cartElement.setAvailable(product.getActive());
        cartElement.setProductCount(1);
        cartElement.setElementPrice(product.getProductPrice());
        cartElement.setTotalPrice(cartElement.getProductCount() * product.getProductPrice());
        return cartElement;
    }

    /**
     * This method checks if product with specified id is already in cart with specified id.
     * @param productId                     specifies product to be checked
     * @param cartId                        specifies cart
     * @return if product has already been added to the cart method returns product id. Otherwise it returns -1
     */
    private Long isProductInCart(Long productId, Long cartId){
        List<CartElement> cartElementList= cartElementDAO.findAll(cartId);
        for (CartElement cartElement : cartElementList){
            if (cartElement.getProduct().getProductId().equals(productId)){
                return cartElement.getId();
            }
        }
        return -1L;
    }
}

package com.javaschool.onlineshop.service.impl;

import com.javaschool.onlineshop.dao.CartDAO;
import com.javaschool.onlineshop.dao.CartElementDAO;
import com.javaschool.onlineshop.dao.CustomerDAO;
import com.javaschool.onlineshop.mappers.CartElementMapper;
import com.javaschool.onlineshop.mappers.ProductMapper;
import com.javaschool.onlineshop.model.dto.CartElementDTO;
import com.javaschool.onlineshop.model.dto.ProductDTO;
import com.javaschool.onlineshop.model.entity.Cart;
import com.javaschool.onlineshop.model.entity.CartElement;
import com.javaschool.onlineshop.model.entity.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.Mockito;

import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceImplTest {

    @Mock
    private CartElementDAO cartElementDAO;

    @Mock
    private CartDAO cartDAO;

    @Mock
    private CustomerDAO customerDAO;

    @Mock
    private CartElementMapper cartElementMapper;

    @Mock
    private ProductMapper productMapper;

    private Cart cart;

    @InjectMocks
    @Spy
    CartServiceImpl cartService;

    @Before
    public void init() {
        this.cart = new Cart();
        Mockito.doReturn(cart).when(cartService).getCart();
        cart.setElementsInCart(3);
        cart.setCartId(5L);
        cart.setCartTotal(1250.50);
        Mockito.when(cartDAO.updateCart(cart)).thenReturn(cart);
    }

    @Test
    public void addCartElement() {
        ProductDTO productDTO = new ProductDTO();
        CartElement cartElement = new CartElement();
        productDTO.setProductId(9L);
        productDTO.setActive(true);
        productDTO.setCategoryId(0);
        productDTO.setProductName("Product");
        Mockito.doReturn(cartElement).when(cartService).createByProduct(productDTO);
        Mockito.when(cartElementDAO.add(cartElement)).thenReturn(cartElement);
        cartService.addCartElement(productDTO);
        Assert.assertEquals(cart.getElementsInCart(), (Integer) 4);
        Assert.assertEquals(cart.getCartId(), cartElement.getCartId());
    }

    @Test
    public void deleteCartElement() {
        CartElement cartElement = new CartElement();
        Mockito.doReturn(cartElement).when(cartElementDAO).delete(anyLong());
        Mockito.doReturn(cart.getCartTotal()).when(cartService).countTotal();
        cartService.deleteCartElement(1L);
        Assert.assertEquals(cart.getElementsInCart(), (Integer) 2);
    }

    @Test
    public void countTotal() {
        List<CartElementDTO> cartElementDTOList = getTestCartElementList();
        Mockito.doReturn(cartElementDTOList).when(cartService).getCartElements();
        Assert.assertEquals((Double) 1295.8, cartService.countTotal());
    }

    private List<CartElementDTO> getTestCartElementList() {
        List<CartElementDTO> cartElementDTOList = new ArrayList<>();
        CartElementDTO cartElementOne = new CartElementDTO();
        cartElementOne.setProductCount(2);
        cartElementOne.setElementPrice(534.0);
        CartElementDTO cartElementTwo = new CartElementDTO();
        cartElementTwo.setProductCount(5);
        cartElementTwo.setElementPrice(45.56);
        cartElementDTOList.add(cartElementOne);
        cartElementDTOList.add(cartElementTwo);
        return cartElementDTOList;
    }

    @Test
    public void updateCartElement() {
        Long cartElementId = 1L;
        Integer quantity = 4;
        CartElement cartElement = new CartElement();
        Mockito.when(cartElementDAO.get(cartElementId)).thenReturn(cartElement);
        cartElement.setElementPrice(55.7);
        Mockito.when(cartElementDAO.update(cartElement)).thenReturn(cartElement);
        Mockito.doReturn(cart.getCartTotal()).when(cartService).countTotal();
        cartService.updateCartElement(cartElementId, quantity);
        Assert.assertEquals(quantity, cartElement.getProductCount());
        Assert.assertEquals((Double) 222.8, cartElement.getTotalPrice());
    }

    @Test
    public void createByProduct() {
        Product product = new Product();
        ProductDTO productDTO = new ProductDTO();
        Mockito.when(productMapper.productDTOToProduct(productDTO)).thenReturn(product);
        product.setActive(true);
        product.setProductPrice(34.55);
        CartElement cartElement = cartService.createByProduct(productDTO);
        Assert.assertEquals(cartElement.getProduct(), product);
        Assert.assertEquals(cartElement.getAvailable(), product.getActive());
        Assert.assertEquals(cartElement.getProductCount(), (Integer) 1);
        Assert.assertEquals(cartElement.getElementPrice(), product.getProductPrice());
        Assert.assertEquals(cartElement.getTotalPrice(), (Double) (cartElement.getProductCount() * product.getProductPrice()));
    }
}





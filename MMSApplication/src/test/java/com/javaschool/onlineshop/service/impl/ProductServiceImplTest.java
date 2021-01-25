package com.javaschool.onlineshop.service.impl;

import com.javaschool.onlineshop.dao.ProductDAO;
import com.javaschool.onlineshop.mappers.ProductMapper;
import com.javaschool.onlineshop.model.entity.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @Mock
    ProductDAO productDAO;

    @Mock
    ProductMapper productMapper;

    @InjectMocks
    @Spy
    ProductServiceImpl productService;

    @Test
    public void decreaseAmount() {
        Long productId = 1L;
        Integer amount = 2;
        Product product = new Product();
        product.setAmountInStock(4);
        product.setActive(true);
        Mockito.when(productDAO.getProductById(productId)).thenReturn(product);
        Mockito.when(productDAO.updateProduct(product)).thenReturn(product);
        productService.decreaseAmount(productId, amount);
        Assert.assertEquals((Integer) 2, product.getAmountInStock());
        Assert.assertEquals(true, product.getActive());
    }
}
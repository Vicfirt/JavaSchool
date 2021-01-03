package com.javaschool.onlineshop.model.entity;


import org.hibernate.annotations.Type;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart_element")
public class CartElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cart_id")
    private Long cartId;

    @OneToOne
    private Product product;

    @Column(name = "product_count")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productCount;

    @Column(name = "element_price")
    private Double elementPrice;

    @Column(name = "is_available")
    @Type(type = "yes_no")
    private Boolean isAvailable = true;

    @Column(name = "element_total_price")
    private Double totalPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Double getElementPrice() {
        return elementPrice;
    }

    public void setElementPrice(Double elementPrice) {
        this.elementPrice = elementPrice;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}


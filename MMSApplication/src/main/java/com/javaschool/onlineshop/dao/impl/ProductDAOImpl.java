package com.javaschool.onlineshop.dao.impl;


import com.javaschool.onlineshop.exception.DataNotFoundException;
import com.javaschool.onlineshop.dao.ProductDAO;
import com.javaschool.onlineshop.model.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class is responsible for getting data from product database entity.
 */
@Repository
public class ProductDAOImpl implements ProductDAO {

    private final SessionFactory sessionFactory;

    public ProductDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Product> findAllProductsByPrice(Double minPrice, Double maxPrice) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Product WHERE productPrice >= :minPrice and productPrice <= :maxPrice";
        return session.createQuery(query, Product.class)
                .setParameter("minPrice", minPrice)
                .setParameter("maxPrice", maxPrice)
                .getResultList();
    }

    @Override
    public List<Product> findAllProducts() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Product ").list();
    }

    @Override
    public Product getProductById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class, id);
        if (product == null) throw new DataNotFoundException("Product with this id does not exist");
        return product;
    }

    @Override
    public Long addProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.update(product);
        return product;
    }

    @Override
    public void deleteProduct(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class, id);
        if (product == null) throw new DataNotFoundException("Product with this id does not exist");
        session.delete(product);
    }

    @Override
    public List<Product> findAllActiveProducts() {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Product WHERE isActive = :active";
        return session.createQuery(query, Product.class)
                .setParameter("active", "Y")
                .getResultList();
    }

    public List<Product> findAllActiveProductsByPrice(Double minPrice, Double maxPrice) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Product WHERE productPrice >= :minPrice and productPrice <= :maxPrice and isActive =: active";
        return session.createQuery(query, Product.class)
                .setParameter("minPrice", minPrice)
                .setParameter("maxPrice", maxPrice)
                .setParameter("active", "Y")
                .getResultList();
    }

    @Override
    public List<Product> findAllActiveProductsByCategory(Integer categoryId) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Product WHERE  categoryId = :categoryId and isActive =: active";
        return session.createQuery(query, Product.class)
                .setParameter("categoryId", categoryId)
                .setParameter("active", "Y")
                .getResultList();
    }

    @Override
    public List<Product> findAllActiveProductsByBrand(String brandName) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Product WHERE productBrand = :brandName and isActive =: active";
        return session.createQuery(query, Product.class)
                .setParameter("brandName", brandName)
                .setParameter("active", "Y")
                .getResultList();
    }

    @Override
    public List<Product> findAllActiveProductsByName(String productName) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Product WHERE productName =: productName and isActive =: active";
        return session.createQuery(query, Product.class)
                .setParameter("productName", productName)
                .setParameter("active", "Y")
                .getResultList();
    }

    @Override
    public List<Product> findSaleProducts() {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Product WHERE isActive =: active order by rand(productId)";
        return session.createQuery(query, Product.class)
                .setParameter("active", "Y")
                .setMaxResults(6)
                .getResultList();
    }
}

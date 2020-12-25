package com.javaschool.onlineshop.dao.impl;


import com.javaschool.onlineshop.dao.ProductDAO;
import com.javaschool.onlineshop.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    private final SessionFactory sessionFactory;

    public ProductDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Product> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Product ").list();
    }

    @Override
    public Product getProductById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Product.class, id);
    }

    @Override
    public void addProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(product);
    }

    @Override
    public void updateProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.update(product);

    }

    @Override
    public void deleteProduct(Product product) {
        product.setActive(false);
        this.updateProduct(product);
    }

    @Override
    public List<Product> findAllActiveProducts() {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Product WHERE isActive = :active";
        return session.createQuery(query, Product.class)
                .setParameter("active", true)
                .getResultList();

    }

    @Override
    public List<Product> findAllActiveProductsByCategory(int categoryId) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Product WHERE isActive = :active AND categoryId = :categoryId";
        return session.createQuery(query, Product.class)
                .setParameter("active", true)
                .setParameter("categoryId", categoryId)
                .getResultList();
    }

    @Override
    public List<Product> findAllActiveProductsByBrandOrModel(String brand, String category) {
        return null;
    }

    @Override
    public List<Product> findAllActiveProductsByName(String productName) {
        return null;
    }
}

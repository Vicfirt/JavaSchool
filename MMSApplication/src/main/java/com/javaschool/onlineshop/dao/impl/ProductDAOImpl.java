package com.javaschool.onlineshop.dao.impl;


import com.javaschool.onlineshop.Exception.MyException;
import com.javaschool.onlineshop.dao.ProductDAO;
import com.javaschool.onlineshop.model.entity.Product;
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
        Product product = session.get(Product.class, id);
        if (product == null) throw new MyException(10, "Product with this id does not exist");
        return product;
    }

    @Override
    public Long addProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.update(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class, id);
        if (product == null) throw new MyException(10, "Product with this id does not exist");
        session.delete(product);
    }

    @Override
    public List<Product> findAllActiveProducts() {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Product WHERE isActive = :active";
        return session.createQuery(query, Product.class)
                .setParameter("active", true)
                .getResultList();
    }

    public List<Product> findAllActiveProductsByPrice(Double minPrice, Double maxPrice) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Product WHERE productPrice >= :minPrice and productPrice <= :maxPrice";
        return session.createQuery(query, Product.class)
                .setParameter("minPrice", minPrice)
                .setParameter("maxPrice", maxPrice)
                .getResultList();
    }

    @Override
    public List<Product> findAllActiveProductsByCategory(Integer categoryId) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Product WHERE  categoryId = :categoryId";
        return session.createQuery(query, Product.class)
                .setParameter("categoryId", categoryId)
                .getResultList();
    }

    @Override
    public List<Product> findAllActiveProductsByBrand(String brandName) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Product WHERE productBrand = :brandName";
        return session.createQuery(query, Product.class)
                .setParameter("brandName", brandName)
                .getResultList();
    }

    @Override
    public List<Product> findAllActiveProductsByName(String productName) {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM Product WHERE productName =:productName";
        return session.createQuery(query, Product.class)
                .setParameter("productName", productName)
                .getResultList();
    }
}

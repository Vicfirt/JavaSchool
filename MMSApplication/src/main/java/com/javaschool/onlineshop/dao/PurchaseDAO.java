package com.javaschool.onlineshop.dao;

import com.javaschool.onlineshop.entity.Purchase;

import java.util.List;

public interface PurchaseDAO {

    List<Purchase> findAll();
}



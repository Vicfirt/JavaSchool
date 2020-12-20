package com.javaschool.onlineshop.service.impl;

import com.javaschool.onlineshop.dao.PurchaseDAO;
import com.javaschool.onlineshop.service.PurchaseService;
import com.javaschool.onlineshop.entity.Purchase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {


    public PurchaseDAO purchaseDAO;


    public PurchaseServiceImpl(PurchaseDAO purchaseDAO) {
        this.purchaseDAO = purchaseDAO;
    }

    @Override
    @Transactional
    public List<Purchase> findAll() {
        return purchaseDAO.findAll();
    }

}

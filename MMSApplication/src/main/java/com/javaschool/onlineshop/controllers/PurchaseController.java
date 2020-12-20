package com.javaschool.onlineshop.controllers;


import com.javaschool.onlineshop.service.PurchaseService;
import com.javaschool.onlineshop.entity.Purchase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

/**
 * This class implements the logic of transition to a specific page
 * associated with orders in accordance with the received URL.
 */
@Controller
public class PurchaseController {

    public PurchaseService purchaseService;


    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    /**
     * If the url address points to an order, then a list of orders for
     * further work will be transferred to the display page.
     */
    @GetMapping("/purchases")
    public String getAllPurchases(Model model) {
        List<Purchase> allPurchases = purchaseService.findAll();
        model.addAttribute("orders", allPurchases);
        return "cart";
    }
}

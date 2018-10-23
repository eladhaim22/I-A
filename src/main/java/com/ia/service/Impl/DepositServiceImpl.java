package com.ia.service.Impl;

import com.ia.entity.Product;
import com.ia.entity.User;
import com.ia.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


public class DepositServiceImpl extends DepositService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("{deposit.hasStockUrl}")
    private String hasStockUrl;

    @Value("{deposit.sendPurchaseUrl}")
    private String sendPurchaseUrl;

    public boolean hasStock(String sku,int quantity){
        return restTemplate.getForObject(hasStockUrl, boolean.class);
    }

    public void sendPurchase(User user, Product product, int quantity) {
        WrapperClass wrapperClass = new WrapperClass(user,product ,quantity);
        HttpEntity<WrapperClass> request = new HttpEntity<>(wrapperClass);
        restTemplate.postForEntity(sendPurchaseUrl,request , boolean.class );
    }

    private static class WrapperClass{
        private User user;
        private Product product;
        private int quantity;

        public WrapperClass(User user, Product product, int quantity) {
            this.user = user;
            this.product = product;
            this.quantity = quantity;
        }
    }

}

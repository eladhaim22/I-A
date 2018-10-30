package com.ia.service.Impl;

import com.ia.entity.Product;
import com.ia.entity.Purchase;
import com.ia.entity.User;
import com.ia.externaldto.ExternalDepositeDTO;
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

    public void sendPurchase(Purchase purchase) {
        ExternalDepositeDTO externalDepositeDTO = this.loadExternal(purchase);
        HttpEntity<ExternalDepositeDTO> request = new HttpEntity<>(externalDepositeDTO);
        restTemplate.postForEntity(sendPurchaseUrl,request , boolean.class );
    }

}

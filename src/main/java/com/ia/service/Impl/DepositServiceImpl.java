package com.ia.service.Impl;

import com.ia.entity.Product;
import com.ia.entity.Purchase;
import com.ia.entity.User;
import com.ia.externaldto.ExternalDepositeDTO;
import com.ia.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.messaging.simp.stomp.StompClientSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DepositServiceImpl extends DepositService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${deposit.hasStockUrl}")
    private String hasStockUrl;

    @Value("${deposit.sendPurchaseUrl}")
    private String sendPurchaseUrl;

    public boolean hasStock(String sku,int quantity){
        StockResponse stockResponse = restTemplate.getForObject(hasStockUrl + sku, StockResponse.class);
        if(stockResponse.getCantidad() >= quantity){
            return true;
        }
        return false;
    }

    public void sendPurchase(Purchase purchase) {
        ExternalDepositeDTO externalDepositeDTO = this.loadExternal(purchase);
        HttpEntity<ExternalDepositeDTO> request = new HttpEntity<>(externalDepositeDTO);
        restTemplate.postForEntity(sendPurchaseUrl,request , boolean.class );
    }

    private static class StockResponse{
        private String codBarra;
        private int cantidad;

        public StockResponse() {
        }

        public StockResponse(String codBarra, int cantidad) {
            this.codBarra = codBarra;
            this.cantidad = cantidad;
        }

        public String getCodBarra() {
            return codBarra;
        }

        public void setCodBarra(String codBarra) {
            this.codBarra = codBarra;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }
    }

}

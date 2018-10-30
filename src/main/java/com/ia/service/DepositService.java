package com.ia.service;

import com.ia.entity.Product;
import com.ia.entity.Purchase;
import com.ia.entity.User;
import com.ia.externaldto.ExternalClientDTO;
import com.ia.externaldto.ExternalDepositeDTO;
import com.ia.externaldto.ExternalProductDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


public abstract class DepositService {
    @Value("${store.id}")
    private String storeId;

    public abstract boolean hasStock(String sku, int quantity);
    public abstract void sendPurchase(Purchase purchase) throws Exception;

    protected ExternalDepositeDTO loadExternal(Purchase purchase){
        ExternalProductDTO externalProductDTO = new ExternalProductDTO(purchase.getProduct().getSku(),purchase.getQuantity());
        ExternalClientDTO externalClientDTO =
                new ExternalClientDTO(purchase.getUser().getName(),
                        purchase.getUser().getLastName(),
                        purchase.getUser().getEmail(),
                        purchase.getUser().getPerson().getAddress());
        return new ExternalDepositeDTO(externalClientDTO,externalProductDTO,purchase.getId());
    }
}

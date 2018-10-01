package com.ia.service;

import com.ia.entity.Product;
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
    public abstract void sendPurchase(User user, Product product,int quantity);

    protected ExternalDepositeDTO loadExternal(User user, Product product,int quantity){
        ExternalProductDTO externalProductDTO = new ExternalProductDTO(storeId,product.getSku(),quantity);
        ExternalClientDTO externalClientDTO = new ExternalClientDTO(user.getName(),user.getLastName(),user.getEmail(),user.getPerson().getAddress());
        return new ExternalDepositeDTO(externalClientDTO,externalProductDTO);
    }
}

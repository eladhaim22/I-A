package com.ia.service.Impl;

import com.ia.entity.Product;
import com.ia.entity.Purchase;
import com.ia.entity.User;
import com.ia.externaldto.ExternalDepositeDTO;
import com.ia.service.DepositService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DepositServiceDummy extends DepositService {

    @Value("${deposite.response}")
    private boolean depositeResponse;

    public boolean hasStock(String sku,int quantity){
        return depositeResponse;
    }

    public void sendPurchase(Purchase purchase) throws Exception {
        this.loadExternal(purchase);
    }

}

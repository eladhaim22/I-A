package com.ia.service.Impl;

import com.ia.service.DepositService;
import org.springframework.stereotype.Service;

@Service
public class DepositServiceDummy implements DepositService {

    public int hasStock(String sku,int quantity){
        return 8;
    }
}

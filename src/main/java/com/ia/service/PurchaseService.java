package com.ia.service;

import com.ia.dto.ProductDTO;
import com.ia.dto.PurchasFTPDTO;
import com.ia.dto.PurchaseDTO;
import com.ia.entity.Purchase;
import com.ia.externaldto.ExternalClaimDTO;
import com.ia.mappers.PurchaseMapper;
import com.ia.repository.PurchaseRepository;
import com.ia.service.Impl.DepositServiceDummy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public interface PurchaseService {
     PurchaseDTO getById(Integer id) throws Exception;
     List<PurchaseDTO> getAllByUser();
    Purchase save(PurchaseDTO purchaseDTO);
     List<PurchasFTPDTO> getAllTodayPurchase(Date date);
    boolean purchase(Integer productId,Integer quantity) throws Exception;
    void addClaim(ExternalClaimDTO claimDTO);
}

package com.ia.service;

import com.ia.dto.ProductDTO;
import com.ia.dto.PurchaseDTO;
import com.ia.entity.Purchase;
import com.ia.mappers.PurchaseMapper;
import com.ia.repository.PurchaseRepository;
import com.ia.service.Impl.DepositServiceDummy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface PurchaseService {
     PurchaseDTO getById(Integer id) throws Exception;
     List<PurchaseDTO> getAllByUser();
     void save(PurchaseDTO purchaseDTO);
    boolean purchase(Integer productId) throws Exception;
}

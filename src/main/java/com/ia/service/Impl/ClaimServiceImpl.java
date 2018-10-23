package com.ia.service.Impl;

import com.ia.dto.ClaimDTO;
import com.ia.entity.Claim;
import com.ia.entity.Purchase;
import com.ia.mappers.ClaimMapper;
import com.ia.repository.ClaimRepository;
import com.ia.repository.PurchaseRepository;
import com.ia.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClaimServiceImpl implements ClaimService {

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private ClaimMapper claimMapper;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    @Transactional
    public void updateClaimsFromFTP(List<ClaimDTO> claimDTOList) {
        List<Integer> purchaseIds = claimDTOList.stream().map(c -> c.getPurchaseId()).collect(Collectors.toList());
        List<Purchase> purchases = purchaseRepository.findAllByIdIn(purchaseIds);
        purchases.stream().forEach(p -> p.getClaims().stream().forEach(c -> c.setActive(false)));
        purchaseRepository.saveAll(purchases);
        List<Claim> claims = claimDTOList.stream().map(c -> claimMapper.toModel(c)).collect(Collectors.toList());
        claimRepository.saveAll(claims);
    }
}

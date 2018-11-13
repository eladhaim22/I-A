package com.ia.mappers;

import com.ia.dto.ClaimDTO;
import com.ia.entity.Claim;
import com.ia.repository.ClaimRepository;
import com.ia.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClaimMapper implements IMapper<Claim,ClaimDTO> {

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public ClaimDTO toDTO(Claim model) {
        ClaimDTO claimDTO = new ClaimDTO();
        claimDTO.setId(model.getId());
        claimDTO.setActive(model.isActive());
        claimDTO.setDescription(model.getDescription());
        claimDTO.setState(model.getState());
        claimDTO.setPurchaseId(model.getPurchase().getId());
        return claimDTO;
    }

    @Override
    public Claim toModel(ClaimDTO dto) {
        Claim claim = null;
        if(dto.getId() != null){
            claim = claimRepository.getOne(dto.getId());
        }
        else {
            claim = new Claim();
        }
        claim.setId(dto.getId());
        claim.setActive(dto.isActive());
        claim.setDescription(dto.getDescription());
        claim.setState(dto.getState());
        claim.setPurchase(purchaseRepository.getOne(dto.getPurchaseId()));
        return claim;
    }
}

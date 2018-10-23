package com.ia.service;

import com.ia.dto.ClaimDTO;
import com.ia.entity.Claim;

import java.util.List;

public interface ClaimService {
    void updateClaimsFromFTP(List<ClaimDTO> claimDTOList);
}

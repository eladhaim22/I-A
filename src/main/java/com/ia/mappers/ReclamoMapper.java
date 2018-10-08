package com.ia.mappers;

import com.ia.dto.ClaimDTO;
import com.ia.entity.Claim;
import org.springframework.stereotype.Component;

@Component
public class ReclamoMapper implements IMapper<Claim,ClaimDTO> {

    @Override
    public ClaimDTO toDTO(Claim model) {
        ClaimDTO reclamoDTO = new ClaimDTO();
        reclamoDTO.setId(model.getId());
        reclamoDTO.setActive(model.isActive());
        reclamoDTO.setDescription(model.getDescription());
        reclamoDTO.setState(model.getState());
        reclamoDTO.setType(model.getType());
        return reclamoDTO;
    }

    @Override
    public Claim toModel(ClaimDTO dto) {
        Claim claim = new Claim();
        claim.setId(dto.getId());
        claim.setActive(dto.isActive());
        claim.setDescription(dto.getDescription());
        claim.setState(dto.getState());
        claim.setType(dto.getType());
        return claim;
    }
}

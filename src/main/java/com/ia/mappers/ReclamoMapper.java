package com.ia.mappers;

import com.ia.dto.PurchaseDTO;
import com.ia.dto.ReclamoDTO;
import com.ia.entity.Purchase;
import com.ia.entity.Reclamo;
import com.ia.repository.PurchaseRepository;
import com.ia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReclamoMapper implements IMapper<Reclamo,ReclamoDTO> {

    @Override
    public ReclamoDTO toDTO(Reclamo model) {
        ReclamoDTO reclamoDTO = new ReclamoDTO();
        reclamoDTO.setId(model.getId());
        reclamoDTO.setActive(model.isActive());
        reclamoDTO.setDescription(model.getDescription());
        reclamoDTO.setEstado(model.getEstado());
        reclamoDTO.setTipo(model.getTipo());
        return reclamoDTO;
    }

    @Override
    public Reclamo toModel(ReclamoDTO dto) {
        Reclamo reclamo = new Reclamo();
        reclamo.setId(dto.getId());
        reclamo.setActive(dto.isActive());
        reclamo.setDescription(dto.getDescription());
        reclamo.setEstado(dto.getEstado());
        reclamo.setTipo(dto.getTipo());
        return reclamo;
    }
}

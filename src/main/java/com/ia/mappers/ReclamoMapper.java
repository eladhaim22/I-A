package com.ia.mappers;

import com.ia.dto.PurchaseDTO;
import com.ia.entity.Purchase;
import com.ia.repository.PurchaseRepository;
import com.ia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PurchaseMapper implements IMapper<Purchase,PurchaseDTO> {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PurchaseDTO toDTO(Purchase model) {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO.setId(model.getId());
        purchaseDTO.setPurchaseDate(model.getPurchaseDate());
        purchaseDTO.setProduct(productMapper.toDTO(model.getProduct()));
        purchaseDTO.setUserId(model.getUser().getId());
        purchaseDTO.setProductName(model.getProductName());
        purchaseDTO.setPrice(model.getPrice());
        purchaseDTO.setQuantity(model.getQuantity());
        return purchaseDTO;
    }

    @Override
    public Purchase toModel(PurchaseDTO dto) {
        Purchase purchase = null;
        if(dto.getId() != null){
            purchase = purchaseRepository.getOne(dto.getId());
        }
        else{
            purchase = new Purchase();
        }
        purchase.setUser(userRepository.getOne(dto.getUserId()));
        purchase.setProduct(productMapper.toModel(dto.getProduct()));
        purchase.setPurchaseDate(dto.getPurchaseDate());
        purchase.setProductName(dto.getProductName());
        purchase.setPrice(dto.getPrice());
        purchase.setQuantity(dto.getQuantity());
        return purchase;
    }
}

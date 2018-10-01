package com.ia.service.Impl;

import com.ia.dto.ProductDTO;
import com.ia.dto.PurchaseDTO;
import com.ia.entity.Purchase;
import com.ia.mappers.PurchaseMapper;
import com.ia.repository.PurchaseRepository;
import com.ia.service.DepositService;
import com.ia.service.ProductService;
import com.ia.service.PurchaseService;
import com.ia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private DepositService depositService;

    public PurchaseDTO getById(Integer id) throws Exception {
        Purchase purchase  = purchaseRepository.getOne(id);
        if(purchase != null){
            return purchaseMapper.toDTO(purchase);
        }
        else {
            throw new Exception("La compra no existe");
        }
    }

    public List<PurchaseDTO> getAllByUser(){
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getByMail(userEmail).getId();
        List<Purchase> purchases = purchaseRepository.findAllByUser_Id(userId);
        return purchases.stream().map(p -> purchaseMapper.toDTO(p)).collect(Collectors.toList());
    }

    public void save(PurchaseDTO purchaseDTO){
            Purchase purchase = purchaseMapper.toModel(purchaseDTO);
            purchaseRepository.saveAndFlush(purchase);
    }

    public boolean purchase(Integer productId,Integer quantity) throws Exception {
        try {
            PurchaseDTO purchaseDTO = null;
            ProductDTO productDTO = productService.getById(productId);
            if(depositService.hasStock(productDTO.getSku(),quantity)){
                purchaseDTO = createPurchase(productDTO,quantity);
                save(purchaseDTO);
            }
            else{
                return false;
            }
            return true;
        }
        catch (Exception e){
            throw e;
        }
    }

    private PurchaseDTO createPurchase(ProductDTO productDTO,int quantity){
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getByMail(userEmail).getId();
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO.setProduct(productDTO);
        purchaseDTO.setProductName(productDTO.getName());
        purchaseDTO.setPurchaseDate(new Date());
        purchaseDTO.setPrice(productDTO.getPrice());
        purchaseDTO.setUserId(userId);
        purchaseDTO.setQuantity(quantity);
        return purchaseDTO;
    }
}

package com.ia.service.Impl;

import com.ia.dto.ProductDTO;
import com.ia.dto.PurchasFTPDTO;
import com.ia.dto.PurchaseDTO;
import com.ia.entity.Claim;
import com.ia.entity.Purchase;
import com.ia.externaldto.ExternalClaimDTO;
import com.ia.mappers.PurchaseMapper;
import com.ia.repository.PurchaseRepository;
import com.ia.service.DepositService;
import com.ia.service.ProductService;
import com.ia.service.PurchaseService;
import com.ia.service.UserService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public Purchase save(PurchaseDTO purchaseDTO){
            Purchase purchase = purchaseMapper.toModel(purchaseDTO);
            return purchaseRepository.saveAndFlush(purchase);
    }

    @Transactional(rollbackOn = ServiceException.class )
    public boolean purchase(Integer productId,Integer quantity) throws Exception {
            PurchaseDTO purchaseDTO = null;
            ProductDTO productDTO = productService.getById(productId);
            if(depositService.hasStock(productDTO.getSku(),quantity)){
                purchaseDTO = createPurchase(productDTO,quantity);
                Purchase purchase = save(purchaseDTO);
                try{
                    depositService.sendPurchase(purchase);
                }
                catch (Exception e){
                    throw new ServiceException(e.getMessage());
                }
            }
            else{
                return false;
            }
            return true;
    }

    public List<PurchasFTPDTO> getAllTodayPurchase(Date date){
        List<Purchase> purchases = purchaseRepository.findAllByPurchaseDateIsGreaterThanEqual(date);
        return purchases.stream().map(p ->
                new PurchasFTPDTO(
                        p.getId(),
                        p.getPrice() * p.getQuantity() ,
                        p.getUser().getPerson().getDni() ,
                        p.getUser().getName() + ' ' + p.getUser().getLastName(),
                        p.getUser().getEmail(),
                        p.getProduct().getId().toString(),
                        p.getProductName())).collect(Collectors.toList());
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

    public void addClaim(ExternalClaimDTO claimDTO){
        Purchase purchase = purchaseRepository.getOne(claimDTO.getIdPedido());
        Claim claim = new Claim();
        claim.setActive(true);
        claim.setPurchase(purchase);
        claim.setDescription(claimDTO.getDescripcion());
        claim.setState(claimDTO.getEstado());
        purchase.getClaims().stream().forEach(c ->
        c.setActive(false));
        purchase.getClaims().add(claim);
        purchaseRepository.save(purchase);
    }
}

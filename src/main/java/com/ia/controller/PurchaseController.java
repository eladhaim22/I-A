package com.ia.controller;

import com.ia.dto.ClaimDTO;
import com.ia.dto.ProductDTO;
import com.ia.dto.PurchaseDTO;
import com.ia.entity.Purchase;
import com.ia.service.ProductService;
import com.ia.service.PurchaseService;
import org.apache.activemq.util.IntSequenceGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

    final private static Logger logger = LoggerFactory.getLogger(PurchaseController.class);

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/list")
    private String listByUser(Model model){
        try{
            logger.info("get all purchases");
            List<PurchaseDTO> purchases = purchaseService.getAllByUser();
            purchases = purchases.stream().map(p -> {
                List<ClaimDTO> claimDTOS = p.getClaims().stream().filter(r -> r.isActive()).collect(Collectors.toList());
                p.setClaims(claimDTOS);
                return p;
            }).collect(Collectors.toList());
            model.addAttribute("purchases",purchases);
            return "purchases/list.html";
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return "";
        }
    }

    @GetMapping({"/{id}"})
    private String getById(@PathVariable(value = "id",required = false) Integer id,Model model) {
        try {
            logger.info("get  with id {0}", id);
            model.addAttribute("purchase",purchaseService.getById(id));
            return "purchases/purchase.html";
        }
        catch (Exception e) {
                logger.error(e.getMessage());
                return "";
        }
    }

    @PostMapping("")
    private ResponseEntity<?> purchase(@RequestBody PurchaseData purchaseData ) {
        try {
            logger.info("making purchase");
            purchaseService.purchase(purchaseData.getProductId(),purchaseData.getQuantity());
            return new ResponseEntity<>(HttpStatus.OK);
         } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static class PurchaseData{
        private Integer productId;
        private Integer quantity;

        public PurchaseData(){}

        public Integer getProductId() {
            return productId;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setProductId(Integer productId) {
            this.productId = productId;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }
    }
}

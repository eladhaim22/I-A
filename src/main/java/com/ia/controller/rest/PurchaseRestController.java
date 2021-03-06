package com.ia.controller.rest;

import com.ia.service.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchase")
public class PurchaseRestController {

    final private static Logger logger = LoggerFactory.getLogger(PurchaseRestController.class);

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("")
    private ResponseEntity<?> purchase(@RequestBody PurchaseData purchaseData ) {
        try {
            logger.info("making purchase");
            boolean hasStock = purchaseService.purchase(purchaseData.getProductId(),purchaseData.getQuantity());
                return new ResponseEntity<>(hasStock,HttpStatus.OK);
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

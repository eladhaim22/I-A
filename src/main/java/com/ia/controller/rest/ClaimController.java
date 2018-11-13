package com.ia.controller.rest;

import com.ia.externaldto.ExternalClaimDTO;
import com.ia.service.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/claim")
public class ClaimController {

    final private static Logger logger = LoggerFactory.getLogger(ClaimController.class);

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/")
    private ResponseEntity saveClaim(@RequestBody ExternalClaimDTO claimDTO){
        try{
            logger.info("save claim to purchase id {}",claimDTO.getIdPedido());
            purchaseService.addClaim(claimDTO);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}

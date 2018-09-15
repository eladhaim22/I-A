package com.ia.controller;

import com.ia.dto.ProductDTO;
import com.ia.dto.PurchaseDTO;
import com.ia.entity.Purchase;
import com.ia.service.ProductService;
import com.ia.service.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
            model.addAttribute("products",purchases);
            return "purchases/list";
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
    private ResponseEntity<?> purchase(@RequestBody Integer productId ) {
        try {
            logger.info("making purchase");
            return new ResponseEntity<>(HttpStatus.OK);
         } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

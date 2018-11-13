package com.ia.controller.rest;

import com.ia.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    final private static Logger logger = LoggerFactory.getLogger(ProductRestController.class);

    @Autowired
    private ProductService productService;

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteById(@PathVariable("id") Integer id) {
        try {
            logger.info("delete products with id {0}", id);
            productService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

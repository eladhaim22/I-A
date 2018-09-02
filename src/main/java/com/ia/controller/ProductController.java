package com.ia.controller;

import com.ia.dto.ProductDTO;
import com.ia.dto.UserDTO;
import com.ia.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("admin/product")
public class ProductController {

    final private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    private String getAll(Model model){
        try{
            logger.info("get all products");
            List<ProductDTO> products = productService.getAll();
            model.addAttribute("products",products);
            return "products/list.html";
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return "";
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        try {
            logger.info("get product with id {0}", id);
            return ResponseEntity.ok(productService.getById(id));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    private ResponseEntity<?> save(@RequestBody ProductDTO productDTO) {
        try {
            logger.info("saving product");
            productService.save(productDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteById(@PathVariable("id") Integer id) {
        try {
            logger.info("delete product with id {0}", id);
            productService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

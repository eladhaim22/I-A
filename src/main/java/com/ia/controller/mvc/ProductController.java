package com.ia.controller.mvc;

import com.ia.dto.ProductDTO;
import com.ia.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/product")
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


    @GetMapping("/listActive")
    private String getAllActive(Model model){
        try{
            logger.info("get all products");
            List<ProductDTO> products = productService.getAllActive();
            model.addAttribute("products",products);
            return "products/list.html";
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return "";
        }
    }

    @GetMapping("/{id}/details")
    private String getDetails(@PathVariable(value = "id") Integer id,Model model) {
        try {
            logger.info("get product with id {0}", id);
            ProductDTO product = null;
            if (id == null) {
                product = new ProductDTO();
            } else {
                product = productService.getById(id);
            }
            model.addAttribute("product",product);
            model.addAttribute("products",productService.getAllActive().subList(0,8 ));
            return "products/details.html";
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return "";
        }
    }

    @GetMapping({"/{id}",""})
    private String getById(@PathVariable(value = "id",required = false) Integer id,Model model) {
        try {
            logger.info("get product with id {0}", id);
            ProductDTO product = null;
            if (id == null) {
                product = new ProductDTO();
            } else {
                product = productService.getById(id);
            }
            model.addAttribute("product",product);
            return "products/product.html";
        }
        catch (Exception e) {
                logger.error(e.getMessage());
                return "";
        }
    }

    @PostMapping("")
    private String save(@Valid @ModelAttribute("product") ProductDTO productDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        try {
                if(bindingResult.hasErrors()){
                    return "products/product.html";
                }
            logger.info("saving products");
            productService.save(productDTO);
            String msg = productDTO.getId() != null ? "productUpdateSuccess" : "productCreateSuccess";
            redirectAttributes.addAttribute("msg", msg);
            return "redirect:product/list";
        } catch (Exception e) {
            logger.error(e.getMessage());
            redirectAttributes.addAttribute("msg", "productSaveError");
            throw e;
        }
    }
}

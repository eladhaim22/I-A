package com.ia.controller;

import com.ia.dto.ProductDTO;
import com.ia.service.ProductService;
import com.ia.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {
    final private static Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private SearchService searchService;

    @GetMapping("")
    private String getAll(Model model, @RequestParam(name="name") String searchTerm){
        try{
            logger.info("get all products");
            List<ProductDTO> products = searchService.fuzzySearch(searchTerm);
            model.addAttribute("products",products);
            return "search/list";
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return "search/list";
        }
    }
}

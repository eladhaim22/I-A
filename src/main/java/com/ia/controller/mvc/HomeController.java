package com.ia.controller.mvc;

import com.ia.service.ProductService;
import com.ia.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @RequestMapping("")
    public String welcome(Model model) {
        model.addAttribute("products",productService.getAllActive().subList(0,8 ));
        return "home";
    }
}

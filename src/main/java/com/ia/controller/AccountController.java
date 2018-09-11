package com.ia.controller;

import com.ia.dto.UserDTO;
import com.ia.entity.User;
import com.ia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage(){
        return "account/login";
    }

    @GetMapping("/register")
    public String registerUser(User newUser,Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "account/register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User newUser, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "account/register";
        }
        userService.saveUser(newUser);
      return "/account/registerSuccess";
    }

    @GetMapping("/register/success")
    public String registerSuccess(){
        return "account/registerSuccess";
    }
}

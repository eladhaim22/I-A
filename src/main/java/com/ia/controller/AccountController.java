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

    @GetMapping("/register")
    public String registerUser(User newUser,@RequestParam("email") String email,RedirectAttributes redirectAttributes,Model model){
        if(email != "") {
            UserDTO user = userService.getByMail(email);
            if (user == null) {
                newUser.setEmail(email);
                model.addAttribute("newUser",newUser);
                return "account/register";
            }
            else{
                redirectAttributes.addAttribute("emailError",true);
            }
        }
        return "redirect:/login";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("newUser") User newUser, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "account/register";
        }
        userService.saveUser(newUser);
      return null;
    }

    @GetMapping("/register/success")
    public String registerSuccess(){
        return "account/registerSuccess";
    }
}

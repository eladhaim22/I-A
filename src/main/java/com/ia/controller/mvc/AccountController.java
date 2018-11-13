package com.ia.controller.mvc;

import com.ia.entity.User;
import com.ia.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.validation.groups.Default;

@Controller
@RequestMapping("/account")
public class AccountController {

    final private static Logger logger = LoggerFactory.getLogger(AccountController.class);

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
        userService.register(newUser);
      return "/account/registerSuccess";
    }

    @GetMapping("/register/success")
    public String registerSuccess(){
        return "account/registerSuccess";
    }

    @GetMapping("/perfil")
    public String myProfile(Model model){
        try{
            User user = userService.getUserByMail(((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
            model.addAttribute("user",user);
            return "account/user";
        } catch (Exception e) {
            logger.error(e.getMessage());
            return"/error";
        }
    }

    @PostMapping("/user")
    public String saveUser(@Validated(Default.class) @ModelAttribute("user") User newUser, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "users/user";
        }
        userService.updateUser(newUser);
        return "home";
    }
}

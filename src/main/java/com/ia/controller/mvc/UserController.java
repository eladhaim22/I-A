package com.ia.controller.mvc;

import com.ia.dto.UserDTO;
import com.ia.entity.User;
import com.ia.service.RoleService;
import com.ia.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.groups.Default;
import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class UserController {

    final private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    private String getAll(Model model){
        try{
            logger.info("get all users");
            List<UserDTO> users = userService.getAll();
            model.addAttribute("users",users);
            model.addAttribute("roles",roleService.getAll());
            return "users/list.html";
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return "";
        }
    }

    @GetMapping({"","/{id}"})
    public String userForm(Model model,@PathVariable(required = false) Long id){
        try{
            User user = null;
            if(id != null){
               user = userService.getUserById(id);
            }
            else{
                user = new User();
            }
            model.addAttribute("user",user);
            model.addAttribute("roles",roleService.getAll());
            return "users/user";
        } catch (Exception e) {
            logger.error(e.getMessage());
            return"/error";
        }
    }

    @PostMapping("")
    public String saveUser(@Validated(Default.class) @ModelAttribute("user") User newUser, BindingResult bindingResult, RedirectAttributes redirectAttributes){
            if(bindingResult.hasErrors()){
            return "users/user";
        }
        userService.saveUser(newUser);
        String msg =newUser.getId() != null ? "userUpdateSuccess" : "userCreateSuccess";
        redirectAttributes.addAttribute("msg", msg);
        return "redirect:/admin/user/list";
    }
}

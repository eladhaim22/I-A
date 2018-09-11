package com.ia.controller;

import com.ia.dto.UserDTO;
import com.ia.entity.Role;
import com.ia.entity.User;
import com.ia.service.RoleService;
import com.ia.service.UserService;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
    public String saveUser(@Valid @ModelAttribute("user") User newUser, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "users/user";
        }
        userService.saveUser(newUser);
        return "redirect:/admin/user/list";
    }

    @PostMapping("/{userId}/role")
    private ResponseEntity<?> toggleRole(@PathVariable("userId") Long userId,@RequestBody Integer[] rolesId){
        try {
            logger.info("adding roles: {0} to user with id {1}", Arrays.stream(rolesId).map(a -> a.toString()).collect(Collectors.joining(", ")),userId);
            userService.addRolesToUser(userId,rolesId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{userId}/active")
    private ResponseEntity<?> toggleActive(@PathVariable("userId") Long userId,@RequestBody boolean active){
        try {
            logger.info("change active state to : {0} user with id {1}",active,userId);
            userService.toggleActive(userId,active);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

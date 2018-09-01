package com.ia.controller;

import com.ia.dto.UserDTO;
import com.ia.entity.Role;
import com.ia.service.RoleService;
import com.ia.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    final private static Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/users")
    private String getAll(Model model){
        try{
            logger.info("get all users");
            List<UserDTO> users = userService.getAll();
            model.addAttribute("users",users);
            model.addAttribute("roles",roleService.getAll());
            return "admin/users.html";
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return "";
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try {
            logger.info("get user with id {0}", id);
            return ResponseEntity.ok(userService.getById(id));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user/{userId}/role")
    private ResponseEntity<?> toggleRole(@PathVariable("userId") Long userId,@RequestBody Integer roleId){
        try {
            Role role = roleService.getById(roleId);
            logger.info("adding roles: {0} to user with id {1}",userId,role.getRole());
            userService.addRolesToUser(userId,role);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

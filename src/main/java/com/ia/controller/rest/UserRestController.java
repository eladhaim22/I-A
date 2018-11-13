package com.ia.controller.rest;

import com.ia.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/user")
public class UserRestController {

    final private static Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    private UserService userService;

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

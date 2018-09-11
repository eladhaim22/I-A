package com.ia.service;

import com.ia.dto.UserDTO;
import com.ia.entity.Role;
import com.ia.entity.User;
import com.ia.mappers.UserMapper;
import com.ia.repository.RoleRepository;
import com.ia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserMapper userMapper;

    public UserDTO getById(Long id) throws Exception {
        User user = userRepository.getOne(id);
        if(user != null){
            return userMapper.toDTO(user);
        }
        else {
            throw new Exception("El usuario no existe");
        }
    }

    public User getUserById(Long id) throws Exception {
        User user = userRepository.getOne(id);
        if(user != null){
            return user;
        }
        else {
            throw new Exception("El usuario no existe");
        }
    }

    public UserDTO getByMail(String email){
        User user = userRepository.findByEmail(email).orElse(null);
        if(user != null){
            return userMapper.toDTO(user);
        }
        else {
            return null;
        }
    }

    public List<UserDTO> getAll(){
        List<User> users = userRepository.findAll();
        return users.stream().map(u -> userMapper.toDTO(u)).collect(Collectors.toList());
    }

    public void addRolesToUser(Long id, Integer[] rolesId) throws Exception {
        User user = userRepository.getOne(id);
        Set<Role> roles = roleRepository.findByIdIn(Arrays.stream(rolesId).collect(Collectors.toList()));
        if(user != null) {
            user.getRoles().clear();
            user.getRoles().addAll(roles);
            userRepository.saveAndFlush(user);
        }
        else {
            throw new Exception("El usuario no existe");
        }
    }

    public void saveUser(User user){
        userRepository.saveAndFlush(user);
    }

    public void toggleActive(Long userId,boolean active) {
        User user = userRepository.getOne(userId);
        user.setActive(active);
        userRepository.saveAndFlush(user);
    }
}

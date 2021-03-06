package com.ia.service.Impl;

import com.ia.dto.UserDTO;
import com.ia.entity.Role;
import com.ia.entity.User;
import com.ia.mappers.UserMapper;
import com.ia.repository.RoleRepository;
import com.ia.repository.UserRepository;
import com.ia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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

    public User getUserByMail(String email){
        User user = userRepository.findByEmail(email).orElse(null);
        if(user != null){
            return user;
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
        if(user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        else{
            user.setPassword(userRepository.getOne(user.getId()).getPassword());
        }
        userRepository.saveAndFlush(user);
    }

    public void updateUser(User updatedUser){
        if(updatedUser.getPassword() != null) {
            updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        else{
            updatedUser.setPassword(userRepository.getOne(updatedUser.getId()).getPassword());
        }
        User user = userRepository.getOne(updatedUser.getId());
        user.setPassword(updatedUser.getPassword());
        user.setLastName(updatedUser.getLastName());
        user.setName(updatedUser.getName());
        user.getPerson().setAddress(updatedUser.getPerson().getAddress());
        user.getPerson().setDni(updatedUser.getPerson().getDni());
        userRepository.saveAndFlush(user);
    }



    public void toggleActive(Long userId,boolean active) {
        User user = userRepository.getOne(userId);
        user.setActive(active);
        userRepository.saveAndFlush(user);
    }

    public void register(User user){
        Role role = roleRepository.findByRole("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.saveAndFlush(user);
    }
}

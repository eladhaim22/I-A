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

public interface UserService {
    UserDTO getById(Long id) throws Exception;
    User getUserById(Long id) throws Exception;
    UserDTO getByMail(String email);
    User getUserByMail(String email);
    List<UserDTO> getAll();
    void addRolesToUser(Long id, Integer[] rolesId) throws Exception;
    void saveUser(User user);
    void toggleActive(Long userId,boolean active);
    void register(User user);
    void updateUser(User newUser);
}

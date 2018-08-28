package com.ia.mappers;

import com.ia.dto.PersonDTO;
import com.ia.dto.UserDTO;
import com.ia.entity.Person;
import com.ia.entity.User;
import com.ia.repository.PersonRepository;
import com.ia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper implements IMapper<User,UserDTO> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PersonMapper personMapper;

    @Override
    public UserDTO toDTO(User model) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(model.getEmail());
        userDTO.setId(model.getId());
        userDTO.setLastName(model.getLastName());
        userDTO.setName(model.getName());
        userDTO.setPerson(personMapper.toDTO(model.getPerson()));
        userDTO.getRoles().clear();
        userDTO.getRoles().addAll(model.getRoles().stream().map(r -> r.getRole()).collect(Collectors.toSet()));
        return userDTO;
    }

    @Override
    public User toModel(UserDTO dto) {
        User user = null;
        if(dto.getId() != null){
            user = userRepository.getOne(dto.getId());
        }
        else{
            user = new User();
        }
        user.setEmail(dto.getEmail());
        user.setLastName(dto.getLastName());
        user.setName(dto.getName());
        user.setPerson(personMapper.toModel(dto.getPerson()));
        return user;
    }
}

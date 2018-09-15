package com.ia.mappers;

import com.ia.dto.PersonDTO;
import com.ia.entity.Person;
import com.ia.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper implements IMapper<Person,PersonDTO> {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public PersonDTO toDTO(Person model) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(model.getId());
        personDTO.setAddress(model.getAddress());
        personDTO.setDni(model.getDni());
        return personDTO;
    }

    @Override
    public Person toModel(PersonDTO dto) {
        Person person = null;
        if(dto.getId() != null){
            person = personRepository.getOne(dto.getId());
        }
        else{
            person = new Person();
        }
        person.setAddress(dto.getAddress());
        person.setDni(dto.getDni());
        return person;
    }
}

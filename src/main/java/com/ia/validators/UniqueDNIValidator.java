package com.ia.validators;

import com.ia.configuration.ApplicationContextProvider;
import com.ia.entity.Person;
import com.ia.entity.User;
import com.ia.repository.PersonRepository;
import com.ia.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UniqueDNIValidator implements ConstraintValidator<UniqueDNI,Person> {

    private PersonRepository personRepository;

    private EntityManager entityManager;

    public UniqueDNIValidator() {
        personRepository = ApplicationContextProvider.getAppContext().getBean(PersonRepository.class);
        entityManager = ApplicationContextProvider.getAppContext().getBean(EntityManager.class);
    }

    public void initialize(UniqueDNIValidator constraint) {
    }

    public boolean isValid(Person person, ConstraintValidatorContext context) {
        try {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Este dni ya esta registrado.").addPropertyNode("dni").addConstraintViolation();
            entityManager.setFlushMode(FlushModeType.COMMIT);
            Optional<Person> personToCompare = personRepository.findByDni(person.getDni());
            if (!personToCompare.isPresent()) {
                return true;
            }
            else if(person.getId() != null && personToCompare.isPresent() && person.getId() == personToCompare.get().getId()) {
                return true;
            }
        } finally {
            entityManager.setFlushMode(FlushModeType.AUTO);
        }
        return false;
    }
}
package com.ia.validators;

import com.ia.configuration.ApplicationContextProvider;
import com.ia.entity.User;
import com.ia.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<Unique,User> {

    private UserRepository userRepository;

    private EntityManager entityManager;

    public UniqueValidator() {
        userRepository = ApplicationContextProvider.getAppContext().getBean(UserRepository.class);
        entityManager = ApplicationContextProvider.getAppContext().getBean(EntityManager.class);
    }

    public void initialize(Unique constraint) {
    }

    public boolean isValid(User user, ConstraintValidatorContext context) {
        try {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Este email ya esta registrado.").addPropertyNode("email").addConstraintViolation();
            entityManager.setFlushMode(FlushModeType.COMMIT);
            if (user.getId() == null) {
                return !userRepository.findByEmail(user.getEmail()).isPresent();
            }
        } finally {
            entityManager.setFlushMode(FlushModeType.AUTO);
        }
        return true;
    }
}

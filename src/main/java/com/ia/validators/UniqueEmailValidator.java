package com.ia.validators;

import com.ia.configuration.ApplicationContextProvider;
import com.ia.entity.User;
import com.ia.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail,User> {

    private UserRepository userRepository;

    private EntityManager entityManager;

    public UniqueEmailValidator() {
        userRepository = ApplicationContextProvider.getAppContext().getBean(UserRepository.class);
        entityManager = ApplicationContextProvider.getAppContext().getBean(EntityManager.class);
    }

    public void initialize(UniqueEmail constraint) {
    }

    public boolean isValid(User user, ConstraintValidatorContext context) {
        try {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Este email ya esta registrado.").addPropertyNode("email").addConstraintViolation();
            entityManager.setFlushMode(FlushModeType.COMMIT);
            Optional<User> userToCompare = userRepository.findByEmail(user.getEmail());
            if (!userToCompare.isPresent()) {
                return true;
            }
            else if(user.getId() != null && userToCompare.isPresent() && user.getId() == userToCompare.get().getId()){
                return true;
            }
        } finally {
            entityManager.setFlushMode(FlushModeType.AUTO);
        }
        return false;
    }
}

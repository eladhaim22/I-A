package com.ia.validators;

import com.ia.configuration.ApplicationContextProvider;
import com.ia.dto.ProductDTO;
import com.ia.entity.Person;
import com.ia.entity.Product;
import com.ia.repository.PersonRepository;
import com.ia.repository.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UniqueSkuValidator implements ConstraintValidator<UniqueSku,ProductDTO> {

    private ProductRepository productRepository;

    private EntityManager entityManager;

    public UniqueSkuValidator() {
        productRepository = ApplicationContextProvider.getAppContext().getBean(ProductRepository.class);
        entityManager = ApplicationContextProvider.getAppContext().getBean(EntityManager.class);
    }

    public void initialize(UniqueSkuValidator constraint) {
    }

    public boolean isValid(ProductDTO product, ConstraintValidatorContext context) {
        try {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("El codigo de barra ya existe.").addPropertyNode("sku").addConstraintViolation();
            entityManager.setFlushMode(FlushModeType.COMMIT);
            Product productToCompare = productRepository.findBySku(product.getSku());
            if (productToCompare == null) {
                return true;
            }
            else if(product.getId() != null && productToCompare != null && productToCompare.getId() == product.getId()) {
                return true;
            }
        } finally {
            entityManager.setFlushMode(FlushModeType.AUTO);
        }
        return false;
    }
}

package com.ia.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueDNIValidator.class)
public @interface UniqueDNI {
    String message() default "Este dni ya esta registrado.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
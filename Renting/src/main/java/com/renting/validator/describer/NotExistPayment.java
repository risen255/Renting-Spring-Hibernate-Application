package com.renting.validator.describer;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.renting.validator.impl.NotExistPaymentValidator;

@Documented
@Constraint(validatedBy = NotExistPaymentValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotExistPayment {
   
    String message() default "Ten rodzaj płatności już istnieje";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};  
}
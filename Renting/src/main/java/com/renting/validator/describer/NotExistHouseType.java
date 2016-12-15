package com.renting.validator.describer;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.renting.validator.impl.NotExistHouseTypeValidator;

@Documented
@Constraint(validatedBy = NotExistHouseTypeValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotExistHouseType {
   
    String message() default "Ten rodzaj domu już istnieje";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};  
}
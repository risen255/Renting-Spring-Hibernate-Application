package com.renting.validator.describer;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.renting.validator.impl.CorrectFormatDateValidator;
import com.renting.validator.impl.NotUsedLoginValidator;

@Documented
@Constraint(validatedBy = CorrectFormatDateValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CorrectFormatDate {
   
    String message() default "Nieprawidłowy format daty";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};  
}
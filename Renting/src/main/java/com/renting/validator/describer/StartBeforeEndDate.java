package com.renting.validator.describer;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.renting.validator.impl.StartBeforeEndDateValidator;

@Documented
@Constraint(validatedBy = StartBeforeEndDateValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface StartBeforeEndDate {
   
    String message() default "Data przyjazdu musi być wcześniejsza od daty wyjazdu";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};  
}
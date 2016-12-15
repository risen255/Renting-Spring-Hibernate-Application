package com.renting.validator.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.renting.action.form.RegisterAction;
import com.renting.validator.describer.PasswordEquality;

public class PasswordEqualityValidator implements ConstraintValidator<PasswordEquality, RegisterAction> {
	
	@Override
	public void initialize(PasswordEquality valid) {
	}

	@Override
	public boolean isValid(RegisterAction registerAction, ConstraintValidatorContext cvx) {
		return (registerAction.getPassword().equals(registerAction.getRepeatPassword())) ? true : false;
	}
}

package com.renting.validator.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.renting.service.UserService;
import com.renting.validator.describer.NotUsedLogin;

public class NotUsedLoginValidator implements ConstraintValidator<NotUsedLogin, String> {
	
	@Autowired
	private UserService userService;

	@Override
	public void initialize(NotUsedLogin valid) {
	}

	@Override
	public boolean isValid(String login, ConstraintValidatorContext cvx) {
		return (userService.findFirstByLogin(login) == null) ? true : false;
	}
}

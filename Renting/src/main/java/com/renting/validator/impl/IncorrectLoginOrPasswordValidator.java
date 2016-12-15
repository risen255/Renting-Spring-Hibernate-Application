package com.renting.validator.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.renting.action.form.LoginAction;
import com.renting.model.User;
import com.renting.service.UserService;
import com.renting.util.MD5;
import com.renting.validator.describer.IncorrentLoginOrPassword;

public class IncorrectLoginOrPasswordValidator implements ConstraintValidator<IncorrentLoginOrPassword, LoginAction> {
	
	@Autowired
	private UserService userService;

	@Override
	public void initialize(IncorrentLoginOrPassword valid) {
	}

	@Override
	public boolean isValid(LoginAction loginAction, ConstraintValidatorContext cvx) {
		User user = userService.findFirstByLogin(loginAction.getLogin());
		return (user != null && user.getPassword().equals(MD5.hashText(loginAction.getPassword()))) ? true : false;
	}
}

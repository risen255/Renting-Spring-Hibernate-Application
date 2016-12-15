package com.renting.validator.impl;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.renting.action.form.ReservationAction;
import com.renting.service.UserService;
import com.renting.util.converter.DateConverter;
import com.renting.validator.describer.StartBeforeEndDate;

public class StartBeforeEndDateValidator implements ConstraintValidator<StartBeforeEndDate, ReservationAction> {
	
	@Autowired
	private UserService userService;

	@Override
	public void initialize(StartBeforeEndDate valid) {
	}

	@Override
	public boolean isValid(ReservationAction reservationAction, ConstraintValidatorContext cvx) {
		Date startDate = new DateConverter(reservationAction.getStartDate()).convertToDate();
		Date endDate = new DateConverter(reservationAction.getEndDate()).convertToDate();
		return ((startDate != null && endDate != null && endDate.after(startDate) == true)) ? true : false;
	}
}

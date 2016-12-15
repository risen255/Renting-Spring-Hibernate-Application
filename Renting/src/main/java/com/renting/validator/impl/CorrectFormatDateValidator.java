package com.renting.validator.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.renting.util.converter.DateConverter;
import com.renting.validator.describer.CorrectFormatDate;

public class CorrectFormatDateValidator implements ConstraintValidator<CorrectFormatDate, String> {
	
	@Override
	public void initialize(CorrectFormatDate valid) {
	}

	@Override
	public boolean isValid(String dateString, ConstraintValidatorContext cvx) {
		return (new DateConverter(dateString).convertToDate() != null) ? true : false;
	}
}

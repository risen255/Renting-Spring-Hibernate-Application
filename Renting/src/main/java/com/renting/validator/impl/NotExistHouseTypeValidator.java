package com.renting.validator.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.renting.service.HouseTypeService;
import com.renting.validator.describer.NotExistHouseType;

public class NotExistHouseTypeValidator implements ConstraintValidator<NotExistHouseType, String> {
	
	@Autowired
	private HouseTypeService houseTypeService;
	
	@Override
	public void initialize(NotExistHouseType valid) {
	}

	@Override
	public boolean isValid(String houseType, ConstraintValidatorContext cvx) {
		return (houseTypeService.findFirstByTypeName(houseType) == null) ? true : false;
	}
}

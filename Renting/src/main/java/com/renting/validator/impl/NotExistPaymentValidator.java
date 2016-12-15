package com.renting.validator.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.renting.service.PaymentService;
import com.renting.validator.describer.NotExistPayment;

public class NotExistPaymentValidator implements ConstraintValidator<NotExistPayment, String> {
	
	@Autowired
	private PaymentService paymentService;
	
	@Override
	public void initialize(NotExistPayment valid) {
	}

	@Override
	public boolean isValid(String paymentType, ConstraintValidatorContext cvx) {
		return (paymentService.findFirstByPaymentType(paymentType) == null) ? true : false;
	}
}

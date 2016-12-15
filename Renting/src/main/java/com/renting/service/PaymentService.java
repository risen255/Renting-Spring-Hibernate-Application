package com.renting.service;

import java.util.List;

import com.renting.action.form.PaymentAction;
import com.renting.model.Payment;

public interface PaymentService extends AbstractService<Payment> {

	List<Payment> findAll();
	
	Payment findFirstByPaymentType(String paymentType);
	
	void addStandardPayment();
	
	void addPayment(PaymentAction paymentAction);
}
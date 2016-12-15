package com.renting.action.form;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import com.renting.validator.describer.NotExistPayment;

@Component
public class PaymentAction {

	@NotEmpty(message = "Pole nie może być puste")
	@NotExistPayment
	private String paymentType;

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
}

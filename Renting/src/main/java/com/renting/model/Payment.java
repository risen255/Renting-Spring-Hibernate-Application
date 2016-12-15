package com.renting.model;

import javax.persistence.Entity;

@Entity
public class Payment extends AbstractEntity {

	private String paymentType;

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
} 

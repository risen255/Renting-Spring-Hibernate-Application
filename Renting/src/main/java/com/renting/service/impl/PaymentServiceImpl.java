package com.renting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renting.action.form.PaymentAction;
import com.renting.model.Payment;
import com.renting.repository.PaymentRepository;
import com.renting.service.PaymentService;

@Service
public class PaymentServiceImpl extends AbstractServiceImpl<Payment> implements PaymentService {

	private PaymentRepository paymentRepository;

	@Autowired
	public PaymentServiceImpl(PaymentRepository abstractRepository) {
		super(abstractRepository);
		this.paymentRepository = abstractRepository;
	}

	@Override
	public List<Payment> findAll() {
		return paymentRepository.findAll();
	}

	@Override
	public void addStandardPayment() {
		Payment payment = new Payment();
		payment.setPaymentType("Gotówka");
		paymentRepository.save(payment);
	}

	@Override
	public void addPayment(PaymentAction paymentAction) {
		Payment payment = new Payment();
		payment.setPaymentType(paymentAction.getPaymentType());
		paymentRepository.save(payment);
	}

	@Override
	public Payment findFirstByPaymentType(String paymentType) {
		return paymentRepository.findFirstByPaymentType(paymentType);
	}
} 
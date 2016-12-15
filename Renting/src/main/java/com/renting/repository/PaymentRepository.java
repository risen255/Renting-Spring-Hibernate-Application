package com.renting.repository;

import com.renting.model.Payment;

public interface PaymentRepository extends AbstractRepository<Payment> {

	Payment findFirstByPaymentType(String paymentType);
}
 
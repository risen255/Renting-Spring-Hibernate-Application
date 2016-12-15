package com.renting.action.form;

import org.springframework.stereotype.Component;

import com.renting.validator.describer.CorrectFormatDate;
import com.renting.validator.describer.StartBeforeEndDate;

@Component
@StartBeforeEndDate
public class ReservationAction {

	@CorrectFormatDate
	private String startDate;
	
	@CorrectFormatDate
	private String endDate;
	
	private String paymentTypeName;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPaymentTypeName() {
		return paymentTypeName;
	}

	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}
}

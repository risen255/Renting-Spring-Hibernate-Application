package com.renting.action.form;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import com.renting.validator.describer.NotExistHouseType;

@Component
public class HouseTypeAction {

	@NotEmpty(message = "Pole nie może być puste")
	@NotExistHouseType
	private String houseType;

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
}

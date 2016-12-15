package com.renting.action.form;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Component
public class RoomAction {
	
	private String houseStringID;

	@NotEmpty(message = "Nie może być puste")
	private String name;

	@Min(value = 1, message = "Musi mieć conajmniej jeden metr kwadratowy")
	private double area;

	@Min(value = 1, message = "Cena za dzień powinna wynosić conajmniej 1 PLN")
	private int pricePerDay;
	
	public String getHouseStringID() {
		return houseStringID;
	}

	public void setHouseStringID(String houseStringID) {
		this.houseStringID = houseStringID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public int getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(int pricePerDay) {
		this.pricePerDay = pricePerDay;
	}
}

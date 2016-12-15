package com.renting.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Room extends AbstractEntity {

	private String name;
	private double area;
	private int pricePerDay;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private House house;
	 
	@OneToMany(cascade = CascadeType.ALL)
	private List<Opinion> opinionList;

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

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public List<Opinion> getOpinionList() {
		return opinionList;
	}

	public void setOpinionList(List<Opinion> opinionList) {
		this.opinionList = opinionList;
	}
}

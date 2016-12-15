package com.renting.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HouseType extends AbstractEntity {

	private String typeName;

	public String getTypeName() {
		return typeName;
	} 

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}

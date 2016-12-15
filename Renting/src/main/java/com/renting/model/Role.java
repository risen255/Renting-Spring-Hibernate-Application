package com.renting.model;

import javax.persistence.Entity;

@Entity
public class Role extends AbstractEntity {

	private String roleName;

	public String getRoleName() {
		return roleName;
	} 

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}

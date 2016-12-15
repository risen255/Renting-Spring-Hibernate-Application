package com.renting.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Opinion extends AbstractEntity {

	private String content;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;

	public String getContent() {
		return content;
	} 

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}

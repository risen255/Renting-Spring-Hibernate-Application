package com.renting.action.form;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import com.renting.validator.describer.IncorrentLoginOrPassword;

@Component
@IncorrentLoginOrPassword
public class LoginAction {

	@NotEmpty(message="Nie może być puste")
	private String login;
	
	@NotEmpty(message="Nie może być puste")
	private String password;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

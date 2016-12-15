package com.renting.action.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.renting.validator.describer.NotUsedLogin;
import com.renting.validator.describer.PasswordEquality;

@Component
@PasswordEquality
public class RegisterAction {

	@Size(min = 5, max = 20,message="Musi zawierać od 5 do 20 znaków")
	@NotUsedLogin
	private String login;
	
	@Size(min = 5, max = 20,message="Musi zawierać od 5 do 20 znaków")
	private String password;
	
	@Size(min = 5, max = 20,message="Musi zawierać od 5 do 20 znaków")
	private String repeatPassword;
	
	@Size(min = 5, max = 20,message="Musi zawierać od 5 do 20 znaków")
	private String name;
	
	@Size(min = 5, max = 30,message="Musi zawierać od 5 do 30 znaków")
	private String surname;
	
	@Min(value=18, message="Musisz być osobą pełnoletnią")
	private int age;
	
	private char sex;
	
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
	public String getRepeatPassword() {
		return repeatPassword;
	}
	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
}

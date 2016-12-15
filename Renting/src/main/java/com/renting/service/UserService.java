package com.renting.service;

import com.renting.action.form.LoginAction;
import com.renting.action.form.RegisterAction;
import com.renting.model.House;
import com.renting.model.User;

public interface UserService extends AbstractService<User> {

	User findFirstByLogin(String login);
	
	User findFirstByName(String name);
	
	void registerUser(RegisterAction registerAction);
	
	void loginUser(LoginAction loginAction);
	
	void logoutUser();
	
	User findByHouse(House house);
	
	boolean isAdministrator(User user);
} 
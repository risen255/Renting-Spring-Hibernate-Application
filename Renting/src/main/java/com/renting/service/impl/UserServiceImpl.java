package com.renting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renting.action.form.LoginAction;
import com.renting.action.form.RegisterAction;
import com.renting.model.House;
import com.renting.model.User;
import com.renting.repository.UserRepository;
import com.renting.service.UserService;
import com.renting.session.UserSession;
import com.renting.util.MD5;

@Service
public class UserServiceImpl extends AbstractServiceImpl<User> implements UserService {

	private UserRepository userRepository;
	
	@Autowired
	private UserSession userSession;

	@Autowired
	public UserServiceImpl(UserRepository abstractRepository) {
		super(abstractRepository);
		this.userRepository = abstractRepository;
	}

	@Override
	public User findFirstByName(String name) {
		return userRepository.findFirstByName(name);
	}

	@Override
	public User findFirstByLogin(String login) {
		return userRepository.findFirstByLogin(login);
	}

	@Override
	public void registerUser(RegisterAction registerAction) {
		
		User user = new User();
		user.setLogin(registerAction.getLogin());
		user.setPassword(MD5.hashText(registerAction.getPassword()));
		user.setName(registerAction.getName());
		user.setSurname(registerAction.getSurname());
		user.setAge(registerAction.getAge());
		user.setSex(registerAction.getSex());
		
		userRepository.save(user);
		
		// Automatic login
		userSession.setLogged(true);
		userSession.setUser(user);
	}

	@Override
	public void loginUser(LoginAction loginAction) {
		
		User user = userRepository.findFirstByLogin(loginAction.getLogin());
		if(user != null) {
			userSession.setLogged(true);
			userSession.setUser(user);
		}
	}

	@Override
	public void logoutUser() {
		userSession.setLogged(false);
		userSession.setUser(null);
	}

	@Override
	public User findByHouse(House house) {
		return userRepository.findByHouse(house.getId());
	}

	@Override
	public boolean isAdministrator(User user) {
		return userRepository.isAdministrator(user.getId());
	}
} 
package com.renting.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.renting.model.User;
import com.renting.service.UserService;
import com.renting.session.UserSession;
import com.renting.util.converter.StringConverter;

@Controller
public class UserController {
	
	private static final Logger LOG = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserSession userSession;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/uzytkownik/{id}", method = RequestMethod.GET)
	public String userSelected(Model model, @PathVariable("id") String strID) {
		
		model.addAttribute("userSession", userSession);
		
		Integer userID = new StringConverter(strID).convertToInteger();
		if(userID != null) {
			User user = userService.findById(userID);
			if(user != null) {
				model.addAttribute("user", user);
				return "selectedUser";
			}
		}
		
		return "errors/pageNotFound";
	}
}
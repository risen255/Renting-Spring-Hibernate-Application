package com.renting.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.renting.action.form.RegisterAction;
import com.renting.service.UserService;
import com.renting.session.UserSession;

@Controller
public class RegisterController {
	
	@Autowired
	private UserSession userSession;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/zarejestruj", method = RequestMethod.GET)
	public String registerPage(Model model) {
		
		model.addAttribute("userSession", userSession);
		
		if(userSession.isLogged() == false) {
			model.addAttribute("registerAction", new RegisterAction());
			return "registerAction";
		}
		return "errors/alreadyLogged";
	}
	
	@RequestMapping(value = "/zarejestruj", method = RequestMethod.POST)
	public String registerSubmit(Model model,
								 @ModelAttribute("registerAction")
								 @Valid RegisterAction registerAction,
								 BindingResult result) {
		
		model.addAttribute("userSession", userSession);
		
		if(result.hasErrors() == false) {
			userService.registerUser(registerAction);
			return "registerActionSuccess";
		}
		return "registerAction";
	}
}
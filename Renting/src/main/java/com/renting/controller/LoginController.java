package com.renting.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.renting.action.form.LoginAction;
import com.renting.service.UserService;
import com.renting.session.UserSession;

@Controller
public class LoginController {

	@Autowired
	private UserSession userSession;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/zaloguj", method = RequestMethod.GET)
	public String loginPage(Model model) {
		model.addAttribute("userSession", userSession);
		
		if(userSession.isLogged() == false) {
			model.addAttribute("loginAction", new LoginAction());
			return "loginAction";
		}
		return "errors/alreadyLogged";
	}

	@RequestMapping(value = "/zaloguj", method = RequestMethod.POST)
	public String loginSubmit(Model model,
							  @ModelAttribute("loginAction")
							  @Valid
							  LoginAction loginAction,
							  BindingResult result) {

		model.addAttribute("userSession", userSession);

		if (result.hasErrors() == false) {
			userService.loginUser(loginAction);
			return "loginActionSuccess";
		}
		return "loginAction";
	}
	
	@RequestMapping(value = "/wyloguj", method = RequestMethod.POST)
	public String logoutSubmit(Model model) {
		model.addAttribute("userSession", userSession);
		userService.logoutUser();
		
		return "redirect:/";
	}
}
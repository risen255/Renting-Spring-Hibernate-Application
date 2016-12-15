/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.renting.controller;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.renting.session.UserSession;
 
@Controller
public class ContactController {
	
	private static final Logger LOG = Logger.getLogger(ContactController.class);
	
	@Autowired
	private UserSession userSession;

	@RequestMapping(value = "/kontakt", method = RequestMethod.GET)
	public String roomsPage(Model model) {
		
		model.addAttribute("userSession", userSession);
		return "contact";
	}
	
}

package com.renting.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.renting.action.form.HouseTypeAction;
import com.renting.action.form.PaymentAction;
import com.renting.model.HouseType;
import com.renting.model.Payment;
import com.renting.service.HouseTypeService;
import com.renting.service.PaymentService;
import com.renting.session.UserSession;
 
@Controller
public class PanelAdministratorController {
	
	private static final Logger LOG = Logger.getLogger(PanelAdministratorController.class);
	
	@Autowired
	private UserSession userSession;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private HouseTypeService houseTypeService;
	
	@RequestMapping(value = "/panel", method = RequestMethod.GET)
	public String panelPage(Model model) {
		
		model.addAttribute("userSession", userSession);
		
		if(userSession.isLogged() && userSession.isAdministrator())
			return "panel/panel";
		
		return "errors/pageNotFound";
	}
	
	@RequestMapping(value = "/panel/platnosci", method = RequestMethod.GET)
	public String paymentPanelPage(Model model) {
		
		model.addAttribute("userSession", userSession);
		
		if(userSession.isLogged() && userSession.isAdministrator()) {
			
			List<Payment> paymentList = paymentService.findAll();
			if(paymentList != null) {
				
				if(paymentList.size() == 0) {
					paymentService.addStandardPayment();
					paymentList = paymentService.findAll();
				}
				
				model.addAttribute("paymentList", paymentList);
			}
			
			model.addAttribute("paymentAction", new PaymentAction());
			return "panel/paymentAction";
		}
		
		return "errors/pageNotFound";
	}
	
	@RequestMapping(value = "/panel/platnosci", method = RequestMethod.POST)
	public String paymentSubmit(Model model,
								  @ModelAttribute("paymentAction")
								  @Valid PaymentAction paymentAction,
								  BindingResult result) {
		
		model.addAttribute("userSession", userSession);
		
		if(result.hasErrors() == false) {
			paymentService.addPayment(paymentAction);
		}
		
		//model.addAttribute("paymentAction", new PaymentAction());
		
		List<Payment> paymentList = paymentService.findAll();
		if(paymentList != null) {
			model.addAttribute("paymentList", paymentList);
		}
		
		return "panel/paymentAction";
	}
	
	@RequestMapping(value = "/panel/rodzaje-domow", method = RequestMethod.GET)
	public String houseTypePanelPage(Model model) {
		
		model.addAttribute("userSession", userSession);
		
		if(userSession.isLogged() && userSession.isAdministrator()) {
			
			List<HouseType> houseTypeList = houseTypeService.findAll();
			if(houseTypeList != null) {
				
				if(houseTypeList.size() == 0) {
					houseTypeService.addStandardHouseType();
					houseTypeList = houseTypeService.findAll();
				}

				model.addAttribute("houseTypeList", houseTypeList);
			}
			
			model.addAttribute("houseTypeAction", new HouseTypeAction());
			return "panel/houseTypeAction";
		}
		
		return "errors/pageNotFound";
	}
	
	@RequestMapping(value = "/panel/rodzaje-domow", method = RequestMethod.POST)
	public String houseTypeSubmit(Model model,
								  @ModelAttribute("houseTypeAction")
								  @Valid HouseTypeAction houseTypeAction,
								  BindingResult result) {
		
		model.addAttribute("userSession", userSession);
		
		if(result.hasErrors() == false) {
			houseTypeService.addHouseType(houseTypeAction);	
		}
		
		//model.addAttribute("houseTypeAction", new HouseTypeAction());
		
		List<HouseType> houseTypeList = houseTypeService.findAll();
		if(houseTypeList != null) {
			model.addAttribute("houseTypeList", houseTypeList);
		}
		
		return "panel/houseTypeAction";
	}
}

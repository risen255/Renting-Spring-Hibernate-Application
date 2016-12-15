package com.renting.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.renting.action.form.HouseAction;
import com.renting.model.House;
import com.renting.model.HouseType;
import com.renting.model.Opinion;
import com.renting.model.Room;
import com.renting.service.HouseService;
import com.renting.service.HouseTypeService;
import com.renting.session.UserSession;
 
@Controller
public class HouseController {
	
	private static final Logger LOG = Logger.getLogger(HouseController.class);
	
	@Autowired
	private UserSession userSession;
	
	@Autowired
	private HouseService houseService;
	
	@Autowired
	private HouseTypeService houseTypeService;
	
	@RequestMapping(value = "/domy", method = RequestMethod.GET)
	public String housesPage(Model model) {
		
		model.addAttribute("userSession", userSession);
		
		List<House> houseList = houseService.findAll();
		if(houseList != null && houseList.size() > 0) {
			model.addAttribute("houseList", houseList);
			return "houses";
		}
		
		return "errors/noHouses";
	}
	
	@RequestMapping(value = "/dom/{id}", method = RequestMethod.GET)
	public String selectedHousePage(Model model, @PathVariable("id") String strID) {
		
		model.addAttribute("userSession", userSession);
		
		boolean converted = false;
		Integer houseID = null;
		
		try {
			houseID = Integer.parseInt(strID);
			converted = true;
		} catch(NumberFormatException ex) {
			LOG.error(ex.getMessage(), ex);
		}
		
		if(converted) {
			House house = houseService.findById(houseID);
			if(house != null) {
				model.addAttribute("house", house);
				return "selectedHouse";
			}
		}
		
		return "errors/pageNotFound";
	}

	@RequestMapping(value = "/dom/dodaj", method = RequestMethod.GET)
	public String addHousePage(Model model) {
		
		model.addAttribute("userSession", userSession);	
		
		if(userSession.isLogged() == true) {
			
			List<HouseType> houseTypeList = houseTypeService.findAll();
			if(houseTypeList != null) {
				
				// If system doesn't have any house types then add standard house type
				if(houseTypeList.size() == 0) {
					houseTypeService.addStandardHouseType();
					
					// Get house types once again
					houseTypeList = houseTypeService.findAll();
				}

				model.addAttribute("houseTypeList", houseTypeList);
				model.addAttribute("houseAction", new HouseAction());
				return "houseAction";
			}
		}
		return "errors/mustBeLogged";
	}
	
	@RequestMapping(value = "/dom/dodaj", method = RequestMethod.POST)
	public String addHouseSubmit(Model model,
								 @ModelAttribute("houseAction")
								 @Valid
								 HouseAction houseAction,
								 BindingResult result) {
		
		model.addAttribute("userSession", userSession);
		
		if (result.hasErrors() == false && houseService.saveHouse(houseAction) == true) {
			model.addAttribute("houseAction", houseAction);
			return "houseActionSuccess";
		}
		return "houseAction";
	}
}

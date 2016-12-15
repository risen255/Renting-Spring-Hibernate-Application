package com.renting.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.renting.action.form.ReservationAction;
import com.renting.action.form.RoomAction;
import com.renting.model.House;
import com.renting.model.Image;
import com.renting.model.Opinion;
import com.renting.model.Payment;
import com.renting.model.Room;
import com.renting.model.User;
import com.renting.service.HouseService;
import com.renting.service.ImageService;
import com.renting.service.OpinionService;
import com.renting.service.PaymentService;
import com.renting.service.ReservationService;
import com.renting.service.RoomService;
import com.renting.service.UserService;
import com.renting.session.UserSession;
import com.renting.util.converter.StringConverter;
 
@Controller
public class RoomController {
	
	private static final Logger LOG = Logger.getLogger(RoomController.class);
	
	@Autowired
	private UserSession userSession;
	
	@Autowired
	private HouseService houseService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private OpinionService opinionService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private ImageService imageService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@RequestMapping(value = "/pokoje", method = RequestMethod.GET)
	public String roomsPage(Model model) {
		
		model.addAttribute("userSession", userSession);
		
		List<Room> roomList = roomService.findAll();
		if(roomList != null && roomList.size() > 0) {
			
			// Get the list of not reserved rooms
			roomList = roomList.stream().filter(e -> (reservationService.getActiveReservation(e) == null)).collect(Collectors.toList());
			if(roomList != null && roomList.size() > 0) {
				model.addAttribute("roomList", roomList);
				return "rooms";
			}
		}
		
		return "errors/noRooms";
	}
	
	@RequestMapping(value = "/pokoj/{id}", method = RequestMethod.GET)
	public String selectedRoomPage(Model model, @PathVariable("id") String strID) {
		
		model.addAttribute("userSession", userSession);
		
		StringConverter converter = new StringConverter(strID);
		Integer roomID = converter.convertToInteger();
		
		if(roomID != null) {
			Room room = roomService.findById(roomID);
			if(room != null) {
				model.addAttribute("room", room);
				model.addAttribute("opinionRequest", new Opinion());
	
				List<Image> imageList = imageService.getListOfRoomImages(room);
				model.addAttribute("imageList", imageList);
				
				if(reservationService.getActiveReservation(room) != null) {
					model.addAttribute("reserved", true);
				} else {
					model.addAttribute("reserved", false);
				}
				
				return "selectedRoom";
			}
		}
		
		return "errors/pageNotFound";
	}
	
	@RequestMapping(value = "/pokoj/{id}/opinia/dodaj", method = RequestMethod.POST)
	public String addOpinion(Model model,
							 @PathVariable("id") String strID,
							 @ModelAttribute("opinionRequest") Opinion opinionRequest) {
		
		model.addAttribute("userSession", userSession);
		
		StringConverter converter = new StringConverter(strID);
		Integer roomID = converter.convertToInteger();
		
		if(roomID != null) {
			Room room = roomService.findById(roomID);
			if(room != null) {
				User user = userService.findFirstByLogin(userSession.getUser().getLogin());
				if(user != null) {
					opinionService.saveOpinion(room, user, opinionRequest);
					return "redirect:/pokoj/" + roomID;
				}
			}
		}
		return "errors/pageNotFound";
	}
	
	@RequestMapping(value= "/pokoj/{id}/rezerwacja", method = RequestMethod.GET)
	public String reservationPage(Model model,
								  @PathVariable("id") String strID) {
		
		model.addAttribute("userSession", userSession);
		
		if(userSession.isLogged() == true) {
			StringConverter converter = new StringConverter(strID);
			Integer roomID = converter.convertToInteger();
			
			if(roomID != null) {
				Room room = roomService.findById(roomID);
				if(room != null) {
					if(reservationService.getActiveReservation(room) == null) {
						
						List<Payment> paymentList = paymentService.findAll();
						if(paymentList != null) {
							if(paymentList.size() == 0) {
								paymentService.addStandardPayment();
								paymentList = paymentService.findAll();
							}
							
							model.addAttribute("room", room);
							model.addAttribute("paymentList", paymentList);
							model.addAttribute("reservationAction", new ReservationAction());
							return "reservationAction";
						}
						return "errors/pageNotFound";
					}			
					return "errors/alreadyReserved";
				}
			}
			return "errors/pageNotFound";
		}
		return "errors/mustBeLogged";
	}
	
	@RequestMapping(value = "/pokoj/{id}/rezerwacja", method = RequestMethod.POST)
	public String reservationSubmit(Model model,
									@PathVariable("id") String strID,
									@ModelAttribute("reservationAction")
									@Valid
									ReservationAction reservationAction,
									BindingResult result) {
		
		model.addAttribute("userSession", userSession);
		
		StringConverter converter = new StringConverter(strID);
		Integer roomID = converter.convertToInteger();
		
		if(roomID != null) {
			Room room = roomService.findById(roomID);
			if(room != null) {
				if(reservationService.getActiveReservation(room) == null) {
					if(result.hasErrors() == false) {
						if(reservationService.bookRoom(reservationAction, room) == true) {
							model.addAttribute("reservationAction", reservationAction);
							return "reservationActionSuccess";
						}
					} else {
						List<Payment> paymentList = paymentService.findAll();
						if(paymentList != null && paymentList.size() > 0) {
							model.addAttribute("paymentList", paymentList);
							model.addAttribute("room", room);
							return "reservationAction";
						}
					}
				}
				return "errors/alreadyReserved";
			}
		}
		return "errors/pageNotFound";
	}
	
	@RequestMapping(value = "/pokoj/dodaj", method = RequestMethod.GET)
	public String addRoomPage(Model model) {
		model.addAttribute("userSession", userSession);
		
		if(userSession.isLogged() == true) {
			
			List<House> houseList = houseService.getListOfUserHouses(userSession.getUser());
			if(houseList != null & houseList.size() > 0) {
				model.addAttribute("roomAction", new RoomAction());
				model.addAttribute("houseList", houseList);
				return "roomAction";
			}
			return "errors/noUserHouses";
		}
		return "errors/mustBeLogged";
	}
    
	@RequestMapping(value = "/pokoj/dodaj", method = RequestMethod.POST)
	public String addRoomSubmit(Model model,
							    @RequestParam("uploadingFiles") MultipartFile[] uploadingFiles,
								@ModelAttribute("roomAction")
								@Valid
								RoomAction roomAction,
								BindingResult result) {
		
		model.addAttribute("userSession", userSession);
		if (result.hasErrors() == false) {
			
			if(roomService.saveRoom(roomAction, uploadingFiles) == true) {
				model.addAttribute("roomAction", roomAction);
				return "roomActionSuccess";
			}
		} else {
			if(userSession.isLogged() == true) {
				List<House> houseList = houseService.getListOfUserHouses(userSession.getUser());
				if(houseList != null & houseList.size() > 0) {
					model.addAttribute("houseList", houseList);
					return "roomAction";
				}
				return "errors/noUserHouses";
			}
			return "errors/mustBeLogged";
		}
		return "errors/pageNotFound";
	}
}

package com.renting.controller;

import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.renting.model.HouseType;
import com.renting.model.Payment;
import com.renting.model.Role;
import com.renting.model.Room;
import com.renting.model.User;
import com.renting.repository.HouseTypeRepository;
import com.renting.repository.ImageRepository;
import com.renting.repository.OpinionRepository;
import com.renting.repository.PaymentRepository;
import com.renting.repository.ReservationRepository;
import com.renting.repository.RoleRepository;
import com.renting.repository.UserRepository;
import com.renting.service.ReservationService;
import com.renting.service.RoomService;
import com.renting.session.UserSession;
import java.util.stream.Collectors;
import com.renting.util.MD5;

@Controller
public class HomeController {
	
        private Random randomGenerator;
	
	@Autowired
	private PaymentRepository paymentDAO;
	
	@Autowired
	private RoleRepository roleDAO;
	
	@Autowired
	private UserRepository userDAO;
	
	@Autowired
	private HouseTypeRepository houseTypeDAO;
	
	@Autowired
	private UserSession userSession;
        
        @Autowired
	private RoomService roomService;

        
        @Autowired
	private ReservationService reservationService;


	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String homeTest(Model model) {
		
		model.addAttribute("userSession", userSession);
		
		User admin = userDAO.findFirstByLogin("admin");
		if(admin == null) {
			Payment paymentCash = new Payment();
			paymentCash.setPaymentType("Gotówka");
			
			paymentDAO.save(paymentCash);
			
			Payment paymentCard = new Payment();
			paymentCard.setPaymentType("Karta");
			
			paymentDAO.save(paymentCard);
			
			Role role = new Role();
			role.setRoleName("Administrator");
			
			roleDAO.save(role);
			
			User user = new User();
			user.setLogin("admin");
			user.setPassword(MD5.hashText("admin"));
			user.setName("admin-name");
			user.setSurname("admin-surname");
			user.setAge(18);
			user.setSex('m');
			user.setRole(role);
			
			userDAO.save(user);
			
			HouseType houseTypeHotel = new HouseType();
			houseTypeHotel.setTypeName("Hotel");
			
			houseTypeDAO.save(houseTypeHotel);
			
			HouseType houseTypeFlat = new HouseType();
			houseTypeFlat.setTypeName("Mieszkanie");
			
			houseTypeDAO.save(houseTypeFlat);
		}
		
		return "index";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, String strID) {
		randomGenerator = new Random();
		model.addAttribute("userSession", userSession);
		
		List<Room> roomList = roomService.findAll();
                int index = randomGenerator.nextInt(roomList.size());    
                roomList.remove(index);
                int index2 = randomGenerator.nextInt(roomList.size()); 
                roomList.remove(index2);
		if(roomList != null && roomList.size() > 0) {
			
			// Get the list of not reserved rooms
			roomList = roomList.stream().filter(e -> (reservationService.getActiveReservation(e) == null)).collect(Collectors.toList());
			if(roomList != null && roomList.size() > 0) {
				model.addAttribute("roomList", roomList);
				return "index";
			}
		}	
		return "errors/noRooms";
	}
}
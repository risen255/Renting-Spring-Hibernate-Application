package com.renting.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.renting.action.form.RoomAction;
import com.renting.model.House;
import com.renting.model.Room;
import com.renting.model.User;
import com.renting.repository.HouseRepository;
import com.renting.repository.RoomRepository;
import com.renting.repository.UserRepository;
import com.renting.service.ImageService;
import com.renting.service.RoomService;
import com.renting.session.UserSession;
import com.renting.util.converter.StringConverter;

@Service
public class RoomServiceImpl extends AbstractServiceImpl<Room> implements RoomService {

	private static final Logger LOG = Logger.getLogger(RoomServiceImpl.class);
	
	private RoomRepository roomRepository;
	
	@Autowired
	private HouseRepository houseRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserSession userSession;
	
	@Autowired
	private ImageService imageService;

	@Autowired
	public RoomServiceImpl(RoomRepository abstractRepository) {
		super(abstractRepository);
		this.roomRepository = abstractRepository;
	}

	@Override
	public List<Room> findAll() {
		return roomRepository.findAll();
	}

	@Override
	public boolean saveRoom(RoomAction roomAction, MultipartFile[] uploadingFiles) {
		
		StringConverter converter = new StringConverter(roomAction.getHouseStringID());
		Integer houseID = converter.convertToInteger();
		
		if(userSession.isLogged() == true && houseID != null) {
			House house = houseRepository.findById(houseID);
			User user = userRepository.findById(userSession.getUser().getId());
			if(house != null && user != null) {
				
				User houseUser = userRepository.findByHouse(house.getId());
				
				if(houseUser != null && houseUser.getId().equals(user.getId())) {
					Room room = new Room();
					room.setName(roomAction.getName());
					room.setHouse(house);
					room.setArea(roomAction.getArea());
					room.setPricePerDay(roomAction.getPricePerDay());
					
					roomRepository.save(room);
					imageService.saveImages(uploadingFiles, room);		
					return true;
				}
				
				LOG.debug("User is not the owner of house");
			}
		}
		return false;
	} 
}
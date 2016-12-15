package com.renting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renting.action.form.HouseAction;
import com.renting.model.House;
import com.renting.model.HouseType;
import com.renting.model.User;
import com.renting.repository.HouseRepository;
import com.renting.repository.HouseTypeRepository;
import com.renting.repository.UserRepository;
import com.renting.service.HouseService;
import com.renting.session.UserSession;

@Service
public class HouseServiceImpl extends AbstractServiceImpl<House> implements HouseService {
 
	private HouseRepository houseRepository;
	
	@Autowired
	private HouseTypeRepository houseTypeRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserSession userSession;
	
	@Autowired
	public HouseServiceImpl(HouseRepository abstractRepository) {
		super(abstractRepository);
		this.houseRepository = abstractRepository;
	}

	@Override
	public List<House> findAll() {
		return houseRepository.findAll();
	}

	@Override
	public boolean saveHouse(HouseAction houseAction) {
		
		HouseType houseType = houseTypeRepository.findFirstByTypeName(houseAction.getHouseTypeName());
		if(userSession.isLogged() == true && houseType != null) {
			
			User user = userRepository.findById(userSession.getUser().getId());
			if(user != null) {
				House house = new House();
				house.setName(houseAction.getName());
				house.setCity(houseAction.getCity());
				house.setHouseType(houseType);
				house.setOwner(user);
				
				houseRepository.save(house);
				return true;
			}
		}
		return false;
	}

	@Override
	public List<House> getListOfUserHouses(User user) {
		return houseRepository.getListOfUserHouses(user.getId());
	}
}
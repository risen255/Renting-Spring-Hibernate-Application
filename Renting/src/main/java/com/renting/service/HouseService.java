package com.renting.service;

import java.util.List;

import com.renting.action.form.HouseAction;
import com.renting.model.House;
import com.renting.model.User;

public interface HouseService extends AbstractService<House> {

	List<House> findAll();
	
	boolean saveHouse(HouseAction houseAction);
	
	List<House> getListOfUserHouses(User user);
} 
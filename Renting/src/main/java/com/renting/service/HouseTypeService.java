package com.renting.service;

import java.util.List;

import com.renting.action.form.HouseTypeAction;
import com.renting.model.HouseType;

public interface HouseTypeService extends AbstractService<HouseType> {

	List<HouseType> findAll();
	
	HouseType findFirstByTypeName(String typeName);
	
	void addStandardHouseType();
	
	void addHouseType(HouseTypeAction houseTypeAction);
} 
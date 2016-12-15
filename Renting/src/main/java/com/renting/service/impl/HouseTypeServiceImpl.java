package com.renting.service.impl;

import java.util.List;

import org.apache.catalina.startup.HomesUserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renting.action.form.HouseTypeAction;
import com.renting.model.HouseType;
import com.renting.repository.HouseTypeRepository;
import com.renting.service.HouseTypeService;

@Service
public class HouseTypeServiceImpl extends AbstractServiceImpl<HouseType> implements HouseTypeService {

	private HouseTypeRepository houseTypeRepository;

	@Autowired
	public HouseTypeServiceImpl(HouseTypeRepository abstractRepository) {
		super(abstractRepository);
		this.houseTypeRepository = abstractRepository;
	}

	@Override
	public List<HouseType> findAll() {
		return houseTypeRepository.findAll();
	}
	
	@Override
	public HouseType findFirstByTypeName(String typeName) {
		return houseTypeRepository.findFirstByTypeName(typeName);
	}

	@Override
	public void addStandardHouseType() {
		HouseType houseType = new HouseType();
		houseType.setTypeName("Mieszkanie");
		
		houseTypeRepository.save(houseType);
	}

	@Override
	public void addHouseType(HouseTypeAction houseTypeAction) {
		HouseType houseType = new HouseType();
		houseType.setTypeName(houseTypeAction.getHouseType());
		
		houseTypeRepository.save(houseType);
	}
} 
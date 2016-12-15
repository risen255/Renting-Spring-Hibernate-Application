package com.renting.repository;

import java.util.List;

import com.renting.model.HouseType;

public interface HouseTypeRepository extends AbstractRepository<HouseType> {

	List<HouseType> findAll();
	
	HouseType findFirstByTypeName(String typeName);
}
 
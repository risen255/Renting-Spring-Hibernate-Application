package com.renting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.renting.model.House;

public interface HouseRepository extends AbstractRepository<House> {

	@Query("SELECT h FROM House h WHERE h.owner.id = :userID")
	List<House> getListOfUserHouses(@Param("userID") int userID);
}
 
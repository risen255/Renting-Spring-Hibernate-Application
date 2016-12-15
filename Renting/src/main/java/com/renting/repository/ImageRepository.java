package com.renting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.renting.model.Image;

public interface ImageRepository extends AbstractRepository<Image> {

	@Query("SELECT i FROM Image i, Room r WHERE i.room.id = r.id AND r.id = :roomID")
	List<Image> getListOfRoomImages(@Param("roomID") int roomID);
}
 
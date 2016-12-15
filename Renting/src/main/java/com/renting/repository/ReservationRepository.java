package com.renting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.renting.model.Reservation;

public interface ReservationRepository extends AbstractRepository<Reservation> {

	@Query("SELECT res FROM Reservation res, Room ro WHERE res.room.id = ro.id AND ro.id = :roomID AND CURDATE() <= res.endDate")
	Reservation getActiveReservation(@Param("roomID") int roomID);
}
 
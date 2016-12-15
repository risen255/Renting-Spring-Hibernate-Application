package com.renting.service;

import com.renting.action.form.ReservationAction;
import com.renting.model.Reservation;
import com.renting.model.Room;

public interface ReservationService extends AbstractService<Reservation> {

	Reservation getActiveReservation(Room room);
	
	boolean bookRoom(ReservationAction reservationAction, Room room);
} 
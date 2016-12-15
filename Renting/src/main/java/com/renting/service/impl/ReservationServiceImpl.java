package com.renting.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renting.action.form.ReservationAction;
import com.renting.model.Payment;
import com.renting.model.Reservation;
import com.renting.model.Room;
import com.renting.model.User;
import com.renting.repository.PaymentRepository;
import com.renting.repository.ReservationRepository;
import com.renting.repository.RoomRepository;
import com.renting.repository.UserRepository;
import com.renting.service.ReservationService;
import com.renting.session.UserSession;
import com.renting.util.converter.DateConverter;

@Service
public class ReservationServiceImpl extends AbstractServiceImpl<Reservation> implements ReservationService {

	private ReservationRepository reservationRepository;
	
	@Autowired
	private UserSession userSession;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	public ReservationServiceImpl(ReservationRepository abstractRepository) {
		super(abstractRepository);
		this.reservationRepository = abstractRepository;
	}

	@Override
	public Reservation getActiveReservation(Room room) {
		return reservationRepository.getActiveReservation(room.getId());
	}

	@Override
	public boolean bookRoom(ReservationAction reservationAction, Room room) {
		
		if(userSession.isLogged() == true) {
			Date startDate = new DateConverter(reservationAction.getStartDate()).convertToDate();
			Date endDate = new DateConverter(reservationAction.getEndDate()).convertToDate();
			Payment payment = paymentRepository.findFirstByPaymentType(reservationAction.getPaymentTypeName());
			room = roomRepository.findById(room.getId());
			User user = userRepository.findById(userSession.getUser().getId());
			
			
			if(startDate != null && endDate != null && payment != null && room != null && user != null) {
				Reservation reservation = new Reservation();
				reservation.setRoom(room);
				reservation.setUser(user);
				reservation.setPayment(payment);
				reservation.setStartDate(startDate);
				reservation.setEndDate(endDate);
				reservationRepository.save(reservation);
				return true;
			}
		}
		return false;
	}
} 
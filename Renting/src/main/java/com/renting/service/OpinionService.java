package com.renting.service;

import com.renting.model.Opinion;
import com.renting.model.Room;
import com.renting.model.User;

public interface OpinionService extends AbstractService<Opinion> {

	void saveOpinion(Room room, User user, Opinion opinion);
} 
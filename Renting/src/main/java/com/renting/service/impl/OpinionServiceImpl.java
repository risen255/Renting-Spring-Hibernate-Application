package com.renting.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renting.model.Opinion;
import com.renting.model.Room;
import com.renting.model.User;
import com.renting.repository.OpinionRepository;
import com.renting.repository.RoomRepository;
import com.renting.service.OpinionService;

@Service
public class OpinionServiceImpl extends AbstractServiceImpl<Opinion> implements OpinionService {

	private static final Logger LOG = Logger.getLogger(OpinionServiceImpl.class);
	
	private OpinionRepository opinionRepository;
	
	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	public OpinionServiceImpl(OpinionRepository abstractRepository) {
		super(abstractRepository);
		this.opinionRepository = abstractRepository;
	} 

	@Override
	public void saveOpinion(Room room, User user, Opinion opinion) {
		
		Opinion entityOpinion = new Opinion();
		entityOpinion.setContent(opinion.getContent());
		entityOpinion.setUser(user);
		opinionRepository.save(entityOpinion);
		
		List<Opinion> opinionList = room.getOpinionList();
		opinionList.add(entityOpinion);
		
		room.setOpinionList(opinionList);

		roomRepository.save(room);
	}
}
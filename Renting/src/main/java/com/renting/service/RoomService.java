package com.renting.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.renting.action.form.RoomAction;
import com.renting.model.Room;

public interface RoomService extends AbstractService<Room> {

	List<Room> findAll();
	
	boolean saveRoom(RoomAction roomAction, MultipartFile[] uploadingFiles);
} 
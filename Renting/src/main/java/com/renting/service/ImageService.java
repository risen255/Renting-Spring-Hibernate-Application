package com.renting.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.renting.model.Image;
import com.renting.model.Room;

public interface ImageService extends AbstractService<Image> {

	boolean saveImages(MultipartFile[] uploadingFiles, Room room);
	
	List<Image> getListOfRoomImages(Room room);
} 
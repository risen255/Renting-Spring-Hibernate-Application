package com.renting.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.renting.model.Image;
import com.renting.model.Room;
import com.renting.repository.ImageRepository;
import com.renting.service.ImageService;
import com.renting.util.MD5;

@Service
public class ImageServiceImpl extends AbstractServiceImpl<Image> implements ImageService {

	private static final String PHOTO_IMAGES_PATH = "/src/main/webapp/roomImages/";

	private static final Logger LOG = Logger.getLogger(ImageServiceImpl.class);
	
	private ImageRepository imageRepository;

	@Autowired
	public ImageServiceImpl(ImageRepository abstractRepository) {
		super(abstractRepository);
		this.imageRepository = abstractRepository;
	}

	@Override
	public boolean saveImages(MultipartFile[] uploadingFiles, Room room) {
		if (uploadingFiles != null && uploadingFiles.length > 0) {

			// No uploaded files
			if (uploadingFiles.length == 1 && uploadingFiles[0].getOriginalFilename().isEmpty() == true)
				return false;

			for (MultipartFile uploadedFile : uploadingFiles) {
				uploadImage(uploadedFile, room);
			}
		}
		return false;
	}

	private Image uploadImage(MultipartFile uploadedFile, Room room) {

		String hashedFileNameWithExtension = getHashedFileNameWithExtension(uploadedFile.getOriginalFilename());
		Image image = null;

		try {
			image = new Image();
			image.setName(hashedFileNameWithExtension);
			image.setRoom(room);
		
			File file = new File(System.getProperty("user.dir") + PHOTO_IMAGES_PATH + hashedFileNameWithExtension);
			uploadedFile.transferTo(file);
			
			imageRepository.save(image);
		} catch(IllegalStateException ex) {
			LOG.error(ex.getMessage(), ex);
		} catch(IOException ex) {
			LOG.error(ex.getMessage(), ex);
		}
		return image;
	}
	
	private String getHashedFileNameWithExtension(String fileName) {
		String fileNameWithoutExtension = FilenameUtils.getBaseName(fileName);
		String fileExtension = FilenameUtils.getExtension(fileName);
		
		Random rand = new Random();
		String hashedName = MD5.hashText(FilenameUtils.getName(fileNameWithoutExtension) + rand.nextInt(99999));

		StringBuilder hashedFileNameWithExtension = new StringBuilder();
		hashedFileNameWithExtension.append(hashedName);
		hashedFileNameWithExtension.append(".");
		hashedFileNameWithExtension.append(fileExtension);
		return hashedFileNameWithExtension.toString();
	}

	@Override
	public List<Image> getListOfRoomImages(Room room) {
		return imageRepository.getListOfRoomImages(room.getId());
	}
}
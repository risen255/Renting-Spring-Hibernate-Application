package com.renting.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class MD5 {

	private static final Logger LOG = Logger.getLogger(MD5.class);
	private static final String MD5_INSTANCE = "MD5";
	
	public static String hashText(String text) {

		String hashedText = "";
		MessageDigest md = null;
		
		try {
			md = MessageDigest.getInstance(MD5_INSTANCE);
			md.update(text.getBytes());
		} catch(NoSuchAlgorithmException ex) {
			LOG.error(ex.getMessage(), ex);
		}

		if(md != null) {
			byte byteData[] = md.digest();
	
			// Convert the byte to hex format
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				hexString.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			
			hashedText = hexString.toString();
		}
		return hashedText;
	}
}

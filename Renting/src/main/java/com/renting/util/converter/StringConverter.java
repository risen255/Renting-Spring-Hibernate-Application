package com.renting.util.converter;

import org.apache.log4j.Logger;

import com.renting.controller.RoomController;

public class StringConverter implements SimpleConvertable {

	private static final Logger LOG = Logger.getLogger(StringConverter.class);
	
	private String text;
	
	public StringConverter(String text) {
		this.text = text;
	}
	
	@Override
	public String convertToString() {
		return text;
	}

	@Override
	public Integer convertToInteger() {
		Integer integerObject = null;
		
		try {
			integerObject = Integer.parseInt(text);
		} catch(NumberFormatException ex) {
			LOG.error(ex.getMessage(), ex);
		}
		
		return integerObject;
	}
}

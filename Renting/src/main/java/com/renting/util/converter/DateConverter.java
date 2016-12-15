package com.renting.util.converter;

import java.util.Calendar;
import java.util.Date;

public class DateConverter {

	private String dateString;
	
	public DateConverter(String dateString) {
		this.dateString = dateString;
	}
	
	public Date convertToDate() {

		if(dateString.length() > 0) {
			String[] dateTable = dateString.split("-");
			if(dateTable != null && dateTable.length >= 3) {					
				Integer year = new StringConverter(dateTable[0]).convertToInteger();
				Integer month = new StringConverter(dateTable[1]).convertToInteger();
				Integer day = new StringConverter(dateTable[2]).convertToInteger();
				if(year != null && month != null && day != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.set(year, month, day, 0, 0, 0);
					return calendar.getTime();
				}
			}
		}
		return null;
	}
}

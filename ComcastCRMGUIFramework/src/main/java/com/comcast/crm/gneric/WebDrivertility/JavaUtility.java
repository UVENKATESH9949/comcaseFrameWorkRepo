package com.comcast.crm.gneric.WebDrivertility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	public int getRandomNumber() {
		Random random = new Random();
		
		return random.nextInt(1000);
	}
	
	public String getSystemDateYYYYDDMM() {
		Date dateObject = new Date();
		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		String date = sd.format(dateObject);
		
		return date;
		
	}
	
	public String getRequireDateYYYYDDMM(int days) {
		Date dateObject = new Date();
		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar ca = Calendar.getInstance();
	    ca.setTime(dateObject);   
		
		ca.add(Calendar.DAY_OF_MONTH, days);
		
		String date = sd.format(ca.getTime());
		
		return date;
	}
}

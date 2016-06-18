package com.alok.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtility {

	public static String getDate() {
		String currdate;
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				MyConstants.DATE_FORMATTER);
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		currdate = dateFormat.format(date);
		return currdate;
	}

}

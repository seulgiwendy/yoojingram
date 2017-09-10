package com.gallery.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static Date getCurrentDate() {
		Date d = new Date();
		long time = System.currentTimeMillis();
		d.setTime(time);

		return d;
	}

	public static String getDaysTimeStamp() {

		return getTimeFormatforDays().format(getCurrentDate());
	}

	private static SimpleDateFormat getTimeFormatforDays() {
		return new SimpleDateFormat("YYYY-MM-dd");
	}

	private static SimpleDateFormat getTimeFormatforSeconds() {
		return new SimpleDateFormat("YYYY-MM-dd kk:mm:ss");
	}

}

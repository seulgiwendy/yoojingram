package com.gallery.utils;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class DateUtilsTest {
	
	private static final String CURRENT_DAY = "2017-09-10";
	
	Date d;
	
	@Before
	public void setup() {
		d = new Date();
	}

	@Test
	public void test() {
		long time = System.currentTimeMillis();
		
		assertEquals(time, DateUtils.getCurrentDate().getTime());
		
	}
	
	@Test
	public void getDateFormat() {
		assertEquals(CURRENT_DAY, DateUtils.getDaysTimeStamp());
	}

}

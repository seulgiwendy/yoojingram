package com.gallery.utils;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import static com.gallery.utils.StringUtils.randomString;

public class StringUtilsTest {
	Random rnd;
	private static final int TEST_STRING_LENGTH = 10;
	@Before
	public void setup() {
		rnd = new Random();
	}

	@Test
	public void test() {
		String randomString = randomString(TEST_STRING_LENGTH);
		System.out.println(randomString);
		assertEquals(TEST_STRING_LENGTH, randomString.length());
	}

}

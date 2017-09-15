package com.gallery.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DuplicatedInfoCheckerTest {
	
	private static final String TEST_USERNAME = "wheejuni";
	DuplicatedInfoChecker jic;
	
	@Before
	public void setup() {
		jic = new DuplicatedInfoChecker(TEST_USERNAME, false);
		
	}
	

	@Test
	public void test() {
		assertFalse(jic.isUnique());
		assertEquals(TEST_USERNAME,jic.getId());
	}

}

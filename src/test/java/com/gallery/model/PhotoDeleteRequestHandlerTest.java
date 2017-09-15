package com.gallery.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.gallery.domain.Photo;

public class PhotoDeleteRequestHandlerTest {
	
	PhotoDeleteRequestHandler pdrh;
	List<Photo> photos;
	
	@Before
	public void setup() {
		photos = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			photos.add(new Photo());
		}
	}

	@Test
	public void test() {
		pdrh = PhotoDeleteRequestHandler.getSingleEraseEntity(new Photo());
	}
	
	@Test
	public void multiInstance() {
		pdrh = PhotoDeleteRequestHandler.getMultipleEraseEntity(photos);
	}

}

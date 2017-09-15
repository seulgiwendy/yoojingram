package com.gallery.model;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;

import com.gallery.domain.Admin;
import com.gallery.domain.Photo;

public class ImageLoaderTest {
	
	private static final String TEST_ADMIN_NAME = "wheejuni";
	private static final String TEST_PATH_NAME = "mcmyioxdevlsqvqiytnc.jpeg";
	private static final String FILE_SAVE_PATH = "/Users/codesqaud/Downloads/test.jpeg";
	ImageLoader il;
	BufferedImage bi;
	Admin admin;
	Photo photo;
	
	@Before
	public void setup() {
		admin = new Admin();
		admin.setName(TEST_ADMIN_NAME);
		photo = new Photo();
		photo.setAdmin(admin);
		photo.setPath(TEST_PATH_NAME);
		il = ImageLoader.getInstance(admin, photo);
	}
	
	@Test
	public void test() throws IOException {
		bi = ImageIO.read(il.getBytesFromS3());
		File outputfile = new File(FILE_SAVE_PATH);
		ImageIO.write(bi, "jpeg", outputfile);
	}

}

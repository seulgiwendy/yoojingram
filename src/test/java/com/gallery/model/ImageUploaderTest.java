package com.gallery.model;

import static org.junit.Assert.*;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;

import com.gallery.domain.Admin;

public class ImageUploaderTest {
	
	public static final String TEST_IMG_PATH = "/Users/codesqaud/Downloads/sohye.jpeg";
	Image image;
	BufferedImage imageBuffer;
	Admin admin;
	ImageUploader iu;
	byte[] imgBytes;
	
	@Before
	public void setup() throws IOException {
		admin = Admin.createAdmin("wheejuni", "1234", "seulgiwendy");
		File input = new File(TEST_IMG_PATH);
		imageBuffer = ImageIO.read(input);
		iu = ImageUploader.getUploaderInstance(admin, "jpeg", "image/jpeg");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(imageBuffer, "jpeg", baos);
		imgBytes = baos.toByteArray();
	}
	

	@Test
	public void test() {
		assertTrue(iu.sendImageToS3(imgBytes));
	
	}

}

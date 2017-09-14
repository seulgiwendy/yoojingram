package com.gallery.utils;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import com.gallery.model.ImageUploaderTest;

import static com.gallery.utils.StringUtils.randomString;

public class StringUtilsTest {
	private static final int TEST_STRING_LENGTH = 10;
	
	Random rnd;
	MockMultipartFile file;
	Path path;
	@Before
	public void setup() throws IOException {
		rnd = new Random();
		path = Paths.get(ImageUploaderTest.TEST_IMG_PATH);
		
		byte[] byteArray = Files.readAllBytes(path);
		file = new MockMultipartFile("sohye.jpeg", ImageUploaderTest.TEST_IMG_PATH , "image/jpeg",byteArray);
	}

	@Test
	public void test() {
		String randomString = randomString(TEST_STRING_LENGTH);
		System.out.println(randomString);
		assertEquals(TEST_STRING_LENGTH, randomString.length());
	}
	
	@Test
	public void getExtensionFromMpUpload() {
		assertEquals("jpeg", StringUtils.getExtensionFromMultipart(file));
	}

}

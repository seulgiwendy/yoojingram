package com.gallery.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.multipart.MultipartFile;

public class StringUtils {

	public static String randomString(int length) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < length; i++) {
			char randomChar = (char) ThreadLocalRandom.current().nextInt(97, 123);
			sb.append(randomChar);
		}

		return sb.toString();
	}

	public static String getExtensionFromMultipart(MultipartFile upload) {
		System.out.println(upload.getOriginalFilename());
		System.out.print(upload.getName());
		return (upload.getOriginalFilename().split("\\."))[1];

	}

}

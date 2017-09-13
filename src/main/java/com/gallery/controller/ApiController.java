package com.gallery.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gallery.domain.Admin;
import com.gallery.domain.Photo;
import com.gallery.model.ImageUploader;
import com.gallery.utils.DateUtils;
import com.gallery.utils.SessionInfoUtils;
import com.gallery.utils.StringUtils;


@RestController
public class ApiController {
	
	@PostMapping("/api/upload")
	public Photo uploadPhoto(@RequestParam MultipartFile upload, HttpSession session) throws IOException {
		Admin loggedInAdmin = (Admin)session.getAttribute(SessionInfoUtils.SESSIONED_LOGIN_KEYWORD);
		String extension = StringUtils.getExtensionFromMultipart(upload);
		String contentType = upload.getContentType();
		ImageUploader iu = ImageUploader.getUploaderInstance(loggedInAdmin, extension, contentType);
		
		if (iu.sendImageToS3(upload.getBytes())) {
			Photo photoInfoOnDB = new Photo();
			photoInfoOnDB.setDate(DateUtils.getCurrentDate());
		}
	}

}

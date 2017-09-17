package com.gallery.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.gallery.domain.Admin;
import com.gallery.domain.Photo;
import com.gallery.model.ImageLoader;
import com.gallery.repositories.AdminRepository;
import com.gallery.repositories.PhotoRepository;
import com.gallery.utils.SessionInfoUtils;

@Controller
public class PhotoController {
	@Autowired
	PhotoRepository photoRepo;

	@Autowired
	AdminRepository adminRepo;
	
	@GetMapping("/photo/upload")
	public  String getPhotoUploadPage(HttpSession session, Model model) {
		
		Admin admin = (Admin)session.getAttribute(SessionInfoUtils.SESSIONED_LOGIN_KEYWORD);
		long adminCode = admin.getId();
		if (admin == null) {
			return "user/loginfail";
		}
		model.addAttribute("categories", adminRepo.findOne(adminCode).getCategories());
		return "photo/uploadform";
	}
	
	
	@GetMapping("/photo/{id}")
	public void servePhotos(@PathVariable long id, HttpServletResponse response) throws IOException {
		Photo targetPhoto = photoRepo.findOne(id);
		if (targetPhoto != null) {
			response.setContentType(setMediaType(targetPhoto));
			ImageLoader il = ImageLoader.getInstanceByPhoto(targetPhoto);
			IOUtils.copy(il.getBytesFromS3(), response.getOutputStream());
		}

	}

	private String setMediaType(Photo photo) {
		String extension = (photo.getPath().split("\\."))[1];
		switch (extension) {
		case "jpg":
			return MediaType.IMAGE_JPEG_VALUE;
		case "jpeg":
			return MediaType.IMAGE_JPEG_VALUE;
		case "png":
			return MediaType.IMAGE_PNG_VALUE;
		case "gif":
			return MediaType.IMAGE_PNG_VALUE;
		default:
			return MediaType.IMAGE_JPEG_VALUE;
		}
	}

}

package com.gallery.controller;

import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.gallery.domain.Photo;
import com.gallery.repositories.PhotoRepository;

@SuppressWarnings("deprecation")
public class PhotoController {
	@Autowired
	PhotoRepository photoRepo;
	
	byte[] photoBytes;
	
	
	AmazonS3 s3client = new AmazonS3Client(new ProfileCredentialsProvider());
	
	@GetMapping("/photo/{id}")
	public HttpEntity servePhotos(@PathVariable long id, HttpServletResponse response) {
		Photo targetPhoto = photoRepo.findOne(id);
		if (targetPhoto != null) {
		
		}
		
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
	

}

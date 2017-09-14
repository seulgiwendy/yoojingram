package com.gallery.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.gallery.repositories.PhotoRepository;

public class PhotoController {
	
	@Autowired
	PhotoRepository photoRepo;
	
	

}

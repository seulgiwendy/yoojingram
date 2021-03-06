package com.gallery.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gallery.domain.Admin;
import com.gallery.domain.Category;
import com.gallery.domain.CategoryInfoChecker;
import com.gallery.domain.DuplicatedInfoChecker;
import com.gallery.domain.InvalidSessionChecker;
import com.gallery.domain.Invitation;
import com.gallery.domain.Photo;
import com.gallery.domain.PhotoFailMessage;
import com.gallery.domain.PhotoSuccessMessage;
import com.gallery.domain.PhotoUploadMessage;
import com.gallery.model.ImageUploader;
import com.gallery.model.InvitationGenerator;
import com.gallery.model.PhotoDeleteRequestHandler;
import com.gallery.repositories.AdminRepository;
import com.gallery.repositories.CategoryRepository;
import com.gallery.repositories.InvitationRepository;
import com.gallery.repositories.PhotoRepository;
import com.gallery.utils.DateUtils;
import com.gallery.utils.SessionInfoUtils;
import com.gallery.utils.StringUtils;

@RestController
public class ApiController {
	
	private PhotoUploadMessage pum;

	@Autowired
	PhotoRepository photoRepo;
	
	@Autowired
	CategoryRepository categoryRepo;
	
	@Autowired
	AdminRepository adminRepo;
	
	@Autowired
	InvitationRepository inviteRepo;

	@PostMapping("/api/upload")
	public PhotoUploadMessage uploadPhoto(@RequestParam MultipartFile upload, String title, String description, String category, HttpSession session) throws IOException {
		Admin loggedInAdmin = (Admin)session.getAttribute(SessionInfoUtils.SESSIONED_LOGIN_KEYWORD);
		Category categories = categoryRepo.findByCategory(category);
		String extension = StringUtils.getExtensionFromMultipart(upload);
		String contentType = upload.getContentType();
		ImageUploader iu = ImageUploader.getUploaderInstance(loggedInAdmin, extension, contentType);
		boolean photoUpload = iu.sendImageToS3(upload.getBytes());
		
		if (photoUpload) {
			Photo photoInfoOnDB = new Photo();
			photoInfoOnDB.setAdmin(loggedInAdmin);
			photoInfoOnDB.setCategory(categories);
			photoInfoOnDB.setDate(DateUtils.getDaysTimeStamp());
			photoInfoOnDB.setPath(iu.getUploadedFileName());
			photoInfoOnDB.setDescription(description);
			photoInfoOnDB.setTitle(title);
			
			photoRepo.save(photoInfoOnDB);
			pum = new PhotoSuccessMessage(photoInfoOnDB.getPath(), photoUpload);
			pum.setHtmlForm();
			return pum;
		}
		pum = new PhotoFailMessage("", photoUpload);
		pum.setHtmlForm();
		return pum;
	}
	
	@PostMapping("/api/join/checkid")
	public DuplicatedInfoChecker checkUniqueId(@RequestBody String id) {
		String query = id.replaceAll("=", "");
		if (adminRepo.findByName(query) != null) {
			return new DuplicatedInfoChecker(query, false);
		}
		return new DuplicatedInfoChecker(query, true);
	}
	
	@PostMapping("/api/categories/check")
	public DuplicatedInfoChecker checkUniqueCategory(@RequestBody String category) {
		String query = category.replaceAll("=", "");
		
		if (categoryRepo.findByCategory(query) != null) {
			return new DuplicatedInfoChecker(query, false);
		}
		return new DuplicatedInfoChecker(query, true);
	}
	
	@GetMapping("/api/categories/delete/{id}")
	public DuplicatedInfoChecker deleteRequestedCategory(@PathVariable long id, HttpSession session) {
		Category targetCategory = categoryRepo.findOne(id);
		Admin loginAdmin = (Admin)session.getAttribute(SessionInfoUtils.SESSIONED_LOGIN_KEYWORD);
		List<Photo> photos = new ArrayList<>();
		
		if (targetCategory == null || targetCategory.getAdmin().equals(loginAdmin) == false) {
			return new InvalidSessionChecker(loginAdmin);
		}
		
		photos = targetCategory.getPhotos();
		PhotoDeleteRequestHandler eraser = PhotoDeleteRequestHandler.getMultipleEraseEntity(photos);
		if (eraser.eraseMultiple()) {
			categoryRepo.delete(targetCategory);
			return new DuplicatedInfoChecker(loginAdmin.getName(), true);
		}
		
		return new DuplicatedInfoChecker(loginAdmin.getName(), false);
		
	}
	
	@GetMapping("/api/invitation")
	public Invitation generateInvitation(HttpSession session) {
		if(session.getAttribute(SessionInfoUtils.SESSIONED_LOGIN_KEYWORD) == null) {
			return null;
		}
		InvitationGenerator ig = InvitationGenerator.getInvitationGeneratorfromSession(session);
		return inviteRepo.save(ig.generateInvitation());
	}
	
	@PostMapping("/api/message/checkuser")
	public List<Admin> checkAdminId(@RequestBody String id) {
		String query = id.replaceAll("=", "");
		return adminRepo.findByNameContaining(query);
	}

}

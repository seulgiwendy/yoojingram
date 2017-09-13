package com.gallery.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.gallery.domain.Category;
import com.gallery.domain.Invitation;
import com.gallery.repositories.CategoryRepository;
import com.gallery.repositories.InvitationRepository;

@Controller
public class CategoriesController {
	
	@Autowired
	CategoryRepository categoryRepo;
	
	@GetMapping("/categories/{id}")
	public ModelAndView getCategoriesGallery(@PathVariable long id, HttpSession session) {
		ModelAndView mav;
		Category selectedCategory = categoryRepo.findOne(id);
		Invitation sessionedInvitation = (Invitation)session.getAttribute(LandingPageController.SESSIONED_INVITATION_KEYWORD);
		
		if (selectedCategory != null && sessionedInvitation != null) {
			mav = new ModelAndView("gallery/main");
			mav.addObject("category", selectedCategory);
			mav.addObject("list", sessionedInvitation.getAdmin().getCategories());
		}
		
		
		return new ModelAndView("gallery/fail");
	}

}

package com.gallery.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.gallery.domain.Admin;
import com.gallery.domain.Category;
import com.gallery.domain.Invitation;
import com.gallery.repositories.CategoryRepository;
import com.gallery.repositories.InvitationRepository;
import com.gallery.utils.SessionInfoUtils;

@Controller
public class CategoriesController {

	@Autowired
	CategoryRepository categoryRepo;

	@GetMapping("/categories/{id}")
	public ModelAndView getCategoriesGallery(@PathVariable long id, HttpSession session) {
		ModelAndView mav = new ModelAndView("categories/gallery");
		Category selectedCategory = categoryRepo.findOne(id);
		Invitation sessionedInvitation = (Invitation) session
				.getAttribute(LandingPageController.SESSIONED_INVITATION_KEYWORD);

		if (selectedCategory != null && sessionedInvitation != null) {
			
			if (selectedCategory.getAdmin().equals(sessionedInvitation.getAdmin())) {
			mav = new ModelAndView("gallery/main");
			mav.addObject("category", selectedCategory);
			return mav;
			}
			return new ModelAndView("gallery/noauthorize");
		}

		return new ModelAndView("gallery/fail");
	}

	@GetMapping("/categories/list")
	public String getMyCategories(Model model, HttpSession session) {
		Admin loggedInAdmin = (Admin) session.getAttribute(SessionInfoUtils.SESSIONED_LOGIN_KEYWORD);
		if (loggedInAdmin != null) {
			model.addAttribute("categories", loggedInAdmin.getCategories());
			return "categories/list";
		}
		return "user/loginfail";
	}

}

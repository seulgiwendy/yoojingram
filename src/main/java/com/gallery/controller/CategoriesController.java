package com.gallery.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gallery.domain.Admin;
import com.gallery.domain.Category;
import com.gallery.domain.Invitation;
import com.gallery.repositories.AdminRepository;
import com.gallery.repositories.CategoryRepository;
import com.gallery.repositories.InvitationRepository;
import com.gallery.utils.SessionInfoUtils;
import static com.gallery.utils.DateUtils.*;

@Controller
public class CategoriesController {

	@Autowired
	CategoryRepository categoryRepo;
	
	@Autowired
	AdminRepository adminRepo;
	
	@GetMapping("/categories/new")
	public String getNewCategoriesForm(HttpSession session) {
		if (session.getAttribute(SessionInfoUtils.SESSIONED_LOGIN_KEYWORD) == null) {
			return "user/loginfail";
		}
		
		return "categories/new";
	}
	
	@PostMapping("/categories/new")
	public String setNewCategories(String category, String description, HttpSession session) {
		Category newCategory = new Category();
		newCategory.setCategory(category);
		newCategory.setDescription(description);
		newCategory.setAdmin((Admin)session.getAttribute(SessionInfoUtils.SESSIONED_LOGIN_KEYWORD));
		newCategory.setDate(getDaysTimeStamp());
		
		categoryRepo.save(newCategory);
		
		return "redirect:/user/admin";
	}
	
	@GetMapping("/categories/{id}")
	public ModelAndView getCategoriesGallery(@PathVariable long id, HttpSession session) {
		ModelAndView mav = new ModelAndView("categories/gallery");
		Category selectedCategory = categoryRepo.findOne(id);
		Invitation sessionedInvitation = (Invitation) session
				.getAttribute(LandingPageController.SESSIONED_INVITATION_KEYWORD);

		if (selectedCategory != null && sessionedInvitation != null) {
			
			if (selectedCategory.getAdmin().equals(sessionedInvitation.getAdmin())) {
			mav = new ModelAndView("categories/gallery");
			mav.addObject("category", selectedCategory);
			return mav;
			}
			System.out.println("허가받지 않은 카테고리 접근");
			return new ModelAndView("gallery/noauthorize");
		}

		return new ModelAndView("gallery/fail");
	}

	@GetMapping("/categories/list")
	public String getMyCategories(Model model, HttpSession session) {
		Admin loggedInAdmin = (Admin) session.getAttribute(SessionInfoUtils.SESSIONED_LOGIN_KEYWORD);
		if (loggedInAdmin != null) {
			long fetchNewAdminData = loggedInAdmin.getId();
			model.addAttribute("categories", adminRepo.findOne(fetchNewAdminData).getCategories());
			return "categories/list";
		}
		return "user/loginfail";
	}

}

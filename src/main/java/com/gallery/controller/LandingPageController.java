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
import com.gallery.model.InvitationGenerator;
import com.gallery.repositories.InvitationRepository;
import com.gallery.utils.SessionInfoUtils;

@Controller
public class LandingPageController {

	public static final String CATEGORIES_MODEL_KEYWORD = "categories";
	public static final String SESSIONED_INVITATION_KEYWORD = "invitation";

	@Autowired
	InvitationRepository inviteRepo;

	@GetMapping("/invitation/{permalink}")
	public String getLandingPage(@PathVariable String permalink, HttpSession session) {

		if (permalink.equals("preview") && session.getAttribute(SessionInfoUtils.SESSIONED_LOGIN_KEYWORD) != null) {
			Admin admin = (Admin) session.getAttribute(SessionInfoUtils.SESSIONED_LOGIN_KEYWORD);
			Invitation temporaryInvitation = new Invitation();
			temporaryInvitation.setAdmin(admin);
			session.setAttribute(SESSIONED_INVITATION_KEYWORD, temporaryInvitation);
			return "redirect:/categories/" + admin.getCategories().get(0).getId();

		}

		Invitation invitation = inviteRepo.findByPath(permalink);

		if (invitation != null) {
			session.setAttribute(SESSIONED_INVITATION_KEYWORD, invitation);
			Category invitedCategory = invitation.getAdmin().getCategories().get(0);
			return "redirect:/categories/" + invitedCategory.getId();

		}

		return ("user/linkfail");

	}

	@GetMapping("/preview")
	public ModelAndView getAdminPreviewPage(HttpSession session) {
		ModelAndView mav = new ModelAndView("index.html");

		if (SessionInfoUtils.isLoggedOn(session)) {
			Admin admin = (Admin) session.getAttribute(SessionInfoUtils.SESSIONED_LOGIN_KEYWORD);
			ModelAndView adminMav = new ModelAndView("gallery/main");
			adminMav.addObject(CATEGORIES_MODEL_KEYWORD, admin.getCategories());
			return mav;
		}

		return mav;

	}

}

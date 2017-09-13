package com.gallery.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gallery.domain.Admin;
import com.gallery.model.LoginFromSession;
import com.gallery.repositories.AdminRepository;
import com.gallery.utils.SessionInfoUtils;

@Controller
public class UserController {
	LoginFromSession lfs;

	@Autowired
	AdminRepository adminRepo;

	@GetMapping("/user/login")
	public String getLogin() {
		return "user/login";
	}
	
	@PostMapping("/user/join")
	public String join(String id, String password, String nickname) {
		
		if (id == null || password == null || nickname == null) {
			return "user/joinfail";
		}
		
		adminRepo.save(Admin.createAdmin(id, password, nickname));
		
		return "redirect:/user/login";
	}

	@PostMapping("/user/login")
	public String login(String id, String password, HttpSession session, Model model) {

		Admin loginUser = getLoggedinAdmin(id, password);
		if (loginUser != null) {
			session.setAttribute(SessionInfoUtils.SESSIONED_LOGIN_KEYWORD, loginUser);
			model.addAttribute("username", loginUser.getName());
			return "user/adminpage";

		}

		return "user/loginfail";

	}

	private Admin getLoggedinAdmin(String id, String password) {
		Admin requestedAdmin = adminRepo.findByName(id);
		if (SessionInfoUtils.isCorrectLogin(requestedAdmin, password)) {
			return requestedAdmin;
		}
		return null;
	}

}

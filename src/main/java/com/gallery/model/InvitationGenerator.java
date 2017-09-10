package com.gallery.model;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.gallery.domain.Admin;
import com.gallery.domain.Category;
import com.gallery.domain.Invitation;
import com.gallery.repositories.InvitationRepository;
import com.gallery.utils.StringUtils;

import static com.gallery.utils.StringUtils.*;

public class InvitationGenerator {
	
	private final static int INVITATION_PERMALINK_LENGTH = 9;
	
	Admin admin;
	String path;
	List<Category> categories;
	
	
	private InvitationGenerator(HttpSession session) {
		Admin loggedInUser= (Admin)session.getAttribute("admin");
		this.admin = loggedInUser;
		this.categories = loggedInUser.getCategories();
		
	}
	
	public static InvitationGenerator getInvitationGeneratorfromSession(HttpSession session) {
		return new InvitationGenerator(session);
	}
	
	public Invitation generateInvitation() {
		this.path = StringUtils.randomString(INVITATION_PERMALINK_LENGTH);
		return new Invitation(this.path, this.categories, this.admin);
	}

}

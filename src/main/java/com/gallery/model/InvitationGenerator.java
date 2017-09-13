package com.gallery.model;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.gallery.domain.Admin;
import com.gallery.domain.Category;
import com.gallery.domain.Invitation;
import com.gallery.repositories.InvitationRepository;
import com.gallery.utils.SessionInfoUtils;
import com.gallery.utils.StringUtils;

import static com.gallery.utils.StringUtils.*;

public class InvitationGenerator {
	
	private final static int INVITATION_PERMALINK_LENGTH = 9;
	
	Admin admin;
	String path;
	
	
	private InvitationGenerator(HttpSession session) {
		Admin loggedInUser= (Admin)session.getAttribute(SessionInfoUtils.SESSIONED_LOGIN_KEYWORD);
		this.admin = loggedInUser;
		
	}
	
	public static InvitationGenerator getInvitationGeneratorfromSession(HttpSession session) {
		return new InvitationGenerator(session);
	}
	
	public Invitation generateInvitation() {
		this.path = StringUtils.randomString(INVITATION_PERMALINK_LENGTH);
		return new Invitation(this.path, this.admin);
	}

}

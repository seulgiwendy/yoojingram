package com.gallery.model;

import javax.servlet.http.HttpSession;

import com.gallery.domain.Admin;
import com.gallery.utils.SessionInfoUtils;

import static com.gallery.utils.SessionInfoUtils.*;

public class LoginFromSession {
	
	private HttpSession session;
	private Admin admin;
	
	
	@Deprecated
	public LoginFromSession() {
		
	}
	
	private LoginFromSession (HttpSession session, Admin admin) {
		this.session = session;
		this.admin = admin;
		
	}
	
	public static LoginFromSession generateLoginObject(HttpSession session) {
		
		if (SessionInfoUtils.isLoggedOn(session)) {
			return new LoginFromSession(session, (Admin)session.getAttribute(SESSIONED_LOGIN_KEYWORD));
		}
		return null;
	}
	
	
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	
	

}

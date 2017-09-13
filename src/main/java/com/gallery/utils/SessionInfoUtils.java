package com.gallery.utils;

import javax.servlet.http.HttpSession;

import com.gallery.domain.Admin;

public class SessionInfoUtils {

	public static final String SESSIONED_LOGIN_KEYWORD = "loginuser";

	public static boolean isLoggedOn(HttpSession session) {
		boolean sessioninfo = false;

		if (session.getAttribute(SESSIONED_LOGIN_KEYWORD) != null) {
			sessioninfo = true;
		}

		return sessioninfo;
	}
	
	public static boolean isCorrectLogin(Admin admin, String password) {
		return (admin.getPassword().equals(password));
	}

}

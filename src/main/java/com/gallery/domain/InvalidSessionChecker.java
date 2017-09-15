package com.gallery.domain;

public class InvalidSessionChecker extends DuplicatedInfoChecker{

	public InvalidSessionChecker(String id, boolean unique) {
		super(id, unique);
	}
	
	public InvalidSessionChecker(Admin admin) {
		super(admin.getName(), false);
	}

}

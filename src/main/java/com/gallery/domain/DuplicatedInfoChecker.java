package com.gallery.domain;

public class DuplicatedInfoChecker {
	
	private boolean unique;
	private String id;
	
	public DuplicatedInfoChecker(String id, boolean unique) {
		this.unique = unique;
		this.id = id;
	}

	public boolean isUnique() {
		return this.unique;
	}

	public String getId() {
		return this.id;
	}
	
	
}

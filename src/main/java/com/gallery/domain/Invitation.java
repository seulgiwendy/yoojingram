package com.gallery.domain;

import java.util.List;

import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Invitation {

	private String path;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_invitation_to_admin"))
	private Admin admin;
	
	@JsonIgnore
	private List<Category>categories;
	
	@Deprecated
	public Invitation() {
		
	}
	
	public Invitation(String path, List<Category>categories, Admin admin) {
		this.path = path;
		this.admin = admin;
		this.categories = categories;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

}

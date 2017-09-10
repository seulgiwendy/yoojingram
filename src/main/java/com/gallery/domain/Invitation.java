package com.gallery.domain;

import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Invitation {

	private String path;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_invitation_to_admin"))
	private Admin admin;

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

package com.gallery.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Invitation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String path;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_invitation_to_admin"))
	private Admin admin;
	
	
	@Deprecated
	public Invitation() {
		
	}
	
	public Invitation(String path, Admin admin) {
		this.path = path;
		this.admin = admin;
		
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

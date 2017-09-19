package com.gallery.domain;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.servlet.http.HttpSession;

import com.gallery.controller.LandingPageController;

@Entity
public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String title;
	private String description;
	private String path;
	private String date;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_photo_to_category"))
	private Category category;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_photo_to_admin"))
	private Admin admin;

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Admin getAdmin() {
		return this.admin;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		if (this.title == null) {
			return "";
		}

		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		if (this.description == null) {
			return "";
		}

		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public boolean isAccessiblePhoto(HttpSession session) {
		Invitation sessionedInvitation = (Invitation) session
				.getAttribute(LandingPageController.SESSIONED_INVITATION_KEYWORD);

		if (sessionedInvitation == null) {
			return false;
		}
		return sessionedInvitation.getAdmin().equals(this.admin);
	}

}

package com.gallery.domain;

import java.awt.Image;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String category;
	private String description;
	private String date;
	private String thumbnail;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_category_to_admin"))
	private Admin admin;

	@OneToMany(mappedBy = "category")
	@OrderBy("id asc")
	private List<Photo> photos;

	public List<Photo> getPhotos() {
		return this.photos;
	}
	
	public long getId() {
		return this.id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}



}
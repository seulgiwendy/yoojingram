package com.gallery.domain;

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

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_category_to_admin"))
	private Admin admin;
	
	@OneToMany(mappedBy = "category")
	@OrderBy("id asc")
	private List<Photo> photos;
	
	
	public List<Photo> getPhotos(){
		return this.photos;
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

}

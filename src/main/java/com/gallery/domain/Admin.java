package com.gallery.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	private String password;
	private String nickname;
	
	@OneToMany(mappedBy = "admin")
	@OrderBy("time asc")
	private List<Category> categories;
	
	@Deprecated
	public Admin() {
		
	}
	
	private Admin(String id, String password, String nickname) {
		this.name = id;
		this.password = password;
		this.nickname = nickname;
	}
	
	public static Admin createAdmin(String id, String password, String nickname) {
		return new Admin(id, password, nickname);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	

}

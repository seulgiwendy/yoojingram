package com.gallery.repositories;

import org.springframework.data.repository.CrudRepository;

import com.gallery.domain.Admin;
import com.gallery.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{
	
	Category findByAdmin(Admin admin);
	Category findByCategory(String category);
	void delete(Category category);

}

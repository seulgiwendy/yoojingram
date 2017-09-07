package com.gallery.repositories;

import org.springframework.data.repository.CrudRepository;

import com.gallery.domain.Photo;

public interface PhotoRepository extends CrudRepository<Photo, Long> {
	
	Photo findByTitle(String title);

}

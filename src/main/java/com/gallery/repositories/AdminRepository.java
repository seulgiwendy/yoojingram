package com.gallery.repositories;
import java.util.List;

import org.springframework.data.repository.*;

import com.gallery.domain.Admin;

public interface AdminRepository extends CrudRepository<Admin, Long>{
	
	Admin findByName(String name);
	List<Admin> findByNameContaining(String name);
}

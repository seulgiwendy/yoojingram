package com.gallery.repositories;
import org.springframework.data.repository.*;

import com.gallery.domain.Admin;

public interface AdminRepository extends CrudRepository<Admin, Long>{
	
	Admin findByName(String name);
}

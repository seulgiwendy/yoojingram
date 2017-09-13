package com.gallery.repositories;

import org.springframework.data.repository.CrudRepository;

import com.gallery.domain.Admin;
import com.gallery.domain.Invitation;

public interface InvitationRepository extends CrudRepository<Invitation, Long>{
	
	public Invitation findByAdmin(Admin admin);
	public Invitation findByPath(String path);
}

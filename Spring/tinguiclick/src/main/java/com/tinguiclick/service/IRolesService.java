package com.tinguiclick.service;

import java.util.List;

import com.tinguiclick.model.Roles;

public interface IRolesService {

	public List<Roles> findAll();
	
	
	public Roles findById(Long id);

	
	public Roles save(Roles roles);
	
	
	public void delete(Long id);
	
}

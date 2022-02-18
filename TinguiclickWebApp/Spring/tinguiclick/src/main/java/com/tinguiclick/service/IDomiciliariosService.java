package com.tinguiclick.service;

import java.util.List;

import com.tinguiclick.model.Domiciliarios;

public interface IDomiciliariosService {

	public List<Domiciliarios> findAll();
	
	
	public Domiciliarios findById(Long id);

	
	public Domiciliarios save(Domiciliarios domiciliario);
	
	
	public void delete(Long id);
	
}

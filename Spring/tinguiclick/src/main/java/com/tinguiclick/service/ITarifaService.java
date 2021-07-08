package com.tinguiclick.service;

import java.util.List;

import com.tinguiclick.model.Tarifa;

public interface ITarifaService {

	public List<Tarifa> findAll();
	
	
	public Tarifa findById(Long id);

	
	public Tarifa save(Tarifa tarifa);
	
	
	public void delete(Long id);
	
}

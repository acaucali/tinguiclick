package com.tinguiclick.service;

import java.util.List;

import com.tinguiclick.model.Aliados;

public interface IAliadosService {

	public List<Aliados> findAll();
	
	
	public Aliados findById(Long id);

	
	public Aliados save(Aliados aliado);
	
	
	public void delete(Long id);
	
}

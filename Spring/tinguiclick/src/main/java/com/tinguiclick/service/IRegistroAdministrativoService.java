package com.tinguiclick.service;

import java.util.List;

import com.tinguiclick.model.RegistroAdministrativo;

public interface IRegistroAdministrativoService {

	public List<RegistroAdministrativo> findAll();
	
	
	public RegistroAdministrativo findById(Long id);

	
	public RegistroAdministrativo save(RegistroAdministrativo registro);
	
	
	public void delete(Long id);
	
}

package com.tinguiclick.service;

import java.util.List;

import com.tinguiclick.model.TipoIdentificacion;

public interface ITipoIdentificacionService {

	public List<TipoIdentificacion> findAll();
	
	
	public TipoIdentificacion findById(Long id);

	
	public TipoIdentificacion save(TipoIdentificacion tipoIdentificacion);
	
	
	public void delete(Long id);
	
}

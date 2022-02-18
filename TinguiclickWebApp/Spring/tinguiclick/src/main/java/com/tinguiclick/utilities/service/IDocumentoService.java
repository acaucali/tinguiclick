package com.tinguiclick.utilities.service;

import java.util.List;

import com.tinguiclick.utilities.model.Documento;

public interface IDocumentoService {
	
	public List<Documento> findAll();
	
	
	public Documento findById(Long id);

	
	public Documento save(Documento documento);
	
	
	public void delete(Long id);
	
	
}

package com.tinguiclick.pedidos.service;

import java.util.List;

import com.tinguiclick.pedidos.model.Factura;



public interface IFacturaService {

	public List<Factura> findAll();
	
	
	public Factura findById(Long id);

	
	public Factura save(Factura factura);
	
	
	public void delete(Long id);
	
}

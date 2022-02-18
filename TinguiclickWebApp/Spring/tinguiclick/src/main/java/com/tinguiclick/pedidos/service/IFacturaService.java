package com.tinguiclick.pedidos.service;

import java.util.Date;
import java.util.List;

import com.tinguiclick.pedidos.model.Factura;
import com.tinguiclick.pedidos.model.Pedido;



public interface IFacturaService {

	public List<Factura> findAll();
	
	public List<Factura> findByFechas(Date desde, Date hasta);
	
	public Factura findById(Long id);

	
	public Factura save(Factura factura);
	
	
	public void delete(Long id);
	
}

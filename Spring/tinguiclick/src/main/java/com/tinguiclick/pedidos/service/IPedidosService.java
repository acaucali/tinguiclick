package com.tinguiclick.pedidos.service;

import java.util.Date;
import java.util.List;

import com.tinguiclick.pedidos.model.Pedido;



public interface IPedidosService {

	public List<Pedido> findAll();
	
	
	public List<Pedido> findByFechas(Date desde, Date hasta);
	
	
	public Pedido findById(Long id);

	
	public Pedido save(Pedido pedido);
	
	
	public void delete(Long id);
	
}

package com.tinguiclick.pedidos.service;

import java.util.List;

import com.tinguiclick.pedidos.model.Pedido;



public interface IPedidosService {

	public List<Pedido> findAll();
	
	
	public Pedido findById(Long id);

	
	public Pedido save(Pedido pedido);
	
	
	public void delete(Long id);
	
}

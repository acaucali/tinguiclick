package com.tinguiclick.pedidos.service;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinguiclick.pedidos.dao.IPedidoDao;
import com.tinguiclick.pedidos.model.Pedido;



@Service
public class PedidosService implements IPedidosService{
	
	@Autowired
	private IPedidoDao pedidoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Pedido> findAll() {
		return (List<Pedido>) pedidoDao.findAll(sortByFechaRegistroAsc());
	}
	
	private Sort sortByFechaRegistroAsc() {
	   return new Sort(Sort.Direction.DESC, "fechaRegistro");
	}

	@Override
	@Transactional(readOnly = true)
	public Pedido findById(Long id) {
		
		return pedidoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Pedido> findByAliado(Long id) {
		
		return pedidoDao.findByAliado(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Pedido> findByDomiciliario(Long id) {
		
		return pedidoDao.findByDomiciliario(id);
	}
	
	@Override
	@Transactional
	public Pedido save(Pedido pedido) {
		
		return pedidoDao.save(pedido);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		pedidoDao.deleteById(id);
	}

	@Override
	public List<Pedido> findByFechas(Date desde, Date hasta) {
		
		return pedidoDao.findByFechas(desde, hasta);
	}
	
}

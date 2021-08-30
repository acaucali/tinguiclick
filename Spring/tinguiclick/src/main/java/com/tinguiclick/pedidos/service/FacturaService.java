package com.tinguiclick.pedidos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinguiclick.pedidos.dao.IFacturaDao;
import com.tinguiclick.pedidos.model.Factura;



@Service
public class FacturaService implements IFacturaService{
	
	@Autowired
	private IFacturaDao facturaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Factura> findAll() {
		return (List<Factura>) facturaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Factura findById(Long id) {
		
		return facturaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Factura save(Factura factura) {
		
		return facturaDao.save(factura);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		facturaDao.deleteById(id);
	}
	
}

package com.tinguiclick.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinguiclick.dao.ITarifaDao;

import com.tinguiclick.model.Tarifa;

@Service
public class TarifaService implements ITarifaService{
	
	@Autowired
	private ITarifaDao tarifaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Tarifa> findAll() {
		return (List<Tarifa>) tarifaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Tarifa findById(Long id) {
		
		return tarifaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Tarifa save(Tarifa aliado) {
		
		return tarifaDao.save(aliado);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		tarifaDao.deleteById(id);
	}
	
}

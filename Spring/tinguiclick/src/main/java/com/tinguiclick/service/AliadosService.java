package com.tinguiclick.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinguiclick.dao.IAliadosDao;

import com.tinguiclick.model.Aliados;

@Service
public class AliadosService implements IAliadosService{
	
	@Autowired
	private IAliadosDao aliadosDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Aliados> findAll() {
		return (List<Aliados>) aliadosDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Aliados findById(Long id) {
		
		return aliadosDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Aliados save(Aliados aliado) {
		
		return aliadosDao.save(aliado);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		aliadosDao.deleteById(id);
	}
	
}

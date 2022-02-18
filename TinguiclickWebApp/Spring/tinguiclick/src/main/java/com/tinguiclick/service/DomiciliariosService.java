package com.tinguiclick.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinguiclick.dao.IDomiciliariosDao;

import com.tinguiclick.model.Domiciliarios;

@Service
public class DomiciliariosService implements IDomiciliariosService{
	
	@Autowired
	private IDomiciliariosDao domiciliariosDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Domiciliarios> findAll() {
		return (List<Domiciliarios>) domiciliariosDao.findAll(sortByNombreAsc());
	}
	
	private Sort sortByNombreAsc() {
	   return new Sort(Sort.Direction.ASC, "nombres");
	}

	@Override
	@Transactional(readOnly = true)
	public Domiciliarios findById(Long id) {
		
		return domiciliariosDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Domiciliarios save(Domiciliarios domiciliario) {
		
		return domiciliariosDao.save(domiciliario);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		domiciliariosDao.deleteById(id);
	}
	
}

package com.tinguiclick.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinguiclick.dao.ITipoIdentificacionDao;
import com.tinguiclick.model.TipoIdentificacion;

@Service
public class TipoIdentificacionServiceImpl implements ITipoIdentificacionService{
	
	@Autowired
	private ITipoIdentificacionDao tipoIdentificacionDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<TipoIdentificacion> findAll() {
		return (List<TipoIdentificacion>) tipoIdentificacionDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public TipoIdentificacion findById(Long id) {
		
		return tipoIdentificacionDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public TipoIdentificacion save(TipoIdentificacion tipoIdentificacion) {
		
		return tipoIdentificacionDao.save(tipoIdentificacion);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		tipoIdentificacionDao.deleteById(id);
	}
	
}

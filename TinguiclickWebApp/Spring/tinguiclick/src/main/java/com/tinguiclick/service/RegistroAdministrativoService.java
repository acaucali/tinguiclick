package com.tinguiclick.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinguiclick.dao.IRegistroAdministrativoDao;

import com.tinguiclick.model.RegistroAdministrativo;
import com.tinguiclick.pedidos.model.Pedido;

@Service
public class RegistroAdministrativoService implements IRegistroAdministrativoService{
	
	@Autowired
	private IRegistroAdministrativoDao registroDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<RegistroAdministrativo> findAll() {
		return (List<RegistroAdministrativo>) registroDao.findAll(sortByFechaRegistroAsc());
	}

	private Sort sortByFechaRegistroAsc() {
	   return new Sort(Sort.Direction.ASC, "fechaRegistro");
	}

	@Override
	@Transactional(readOnly = true)
	public RegistroAdministrativo findById(Long id) {
		
		return registroDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public RegistroAdministrativo save(RegistroAdministrativo registro) {
		
		return registroDao.save(registro);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		registroDao.deleteById(id);
	}
	
}

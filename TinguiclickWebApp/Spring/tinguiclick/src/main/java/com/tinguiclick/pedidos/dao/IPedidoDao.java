package com.tinguiclick.pedidos.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tinguiclick.model.Usuario;
import com.tinguiclick.pedidos.model.Pedido;


public interface IPedidoDao extends JpaRepository<Pedido, Long>{

	@Query("select p from Pedido p where p.fechaRegistro >= ?1 and p.fechaRegistro <=?2")
	public List<Pedido> findByFechas(Date fechaInicio, Date fechaFinal);
	
	@Query("select p from Pedido p where p.aliado = ?1")
	public List<Pedido> findByAliado(Long aliado);
	
	@Query("select p from Pedido p where p.domiciliario =?1")
	public List<Pedido> findByDomiciliario(Long domiciliario);
	
}

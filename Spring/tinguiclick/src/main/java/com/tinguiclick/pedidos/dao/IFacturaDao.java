package com.tinguiclick.pedidos.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tinguiclick.pedidos.model.Factura;
import com.tinguiclick.pedidos.model.Pedido;


public interface IFacturaDao extends JpaRepository<Factura, Long>{

	@Query("select f from Factura f where f.fechaRegistro >= ?1 and f.fechaRegistro <=?2")
	public List<Factura> findByFechas(Date fechaInicio, Date fechaFinal);
	
}

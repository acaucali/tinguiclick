package com.tinguiclick.pedidos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tinguiclick.pedidos.model.Factura;


public interface IFacturaDao extends JpaRepository<Factura, Long>{

}

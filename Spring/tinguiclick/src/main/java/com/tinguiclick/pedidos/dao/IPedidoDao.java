package com.tinguiclick.pedidos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tinguiclick.pedidos.model.Pedido;


public interface IPedidoDao extends JpaRepository<Pedido, Long>{

}

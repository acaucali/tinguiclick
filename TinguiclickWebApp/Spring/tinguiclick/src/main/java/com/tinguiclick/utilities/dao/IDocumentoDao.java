package com.tinguiclick.utilities.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tinguiclick.utilities.model.Documento;

public interface IDocumentoDao extends JpaRepository<Documento, Long>{

}

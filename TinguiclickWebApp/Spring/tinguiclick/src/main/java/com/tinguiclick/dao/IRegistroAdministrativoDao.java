package com.tinguiclick.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tinguiclick.model.Aliados;
import com.tinguiclick.model.RegistroAdministrativo;


public interface IRegistroAdministrativoDao extends JpaRepository<RegistroAdministrativo, Long>{

}

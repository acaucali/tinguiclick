package com.tinguiclick.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="registro")
public class RegistroAdministrativo implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long registroId;
	
	@Size(max=1000)
	@Column(nullable=true)
	private String descripcion;
	
	@Column(nullable=true)
	private Long numeroHoras;
	
	@Column(nullable=true)
	private Date fechaRegistro;
	
	@Column(nullable=true)
	private Date fechaModificacion;
	
	@Column(nullable=true)
	private Long usuario;
	
		
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getNumeroHoras() {
		return numeroHoras;
	}

	public void setNumeroHoras(Long numeroHoras) {
		this.numeroHoras = numeroHoras;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Long getUsuario() {
		return usuario;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}	

	public Long getRegistroId() {
		return registroId;
	}

	public void setRegistroId(Long registroId) {
		this.registroId = registroId;
	}



	private static final long serialVersionUID = 1L;
	
}

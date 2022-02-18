package com.tinguiclick.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="tipo_identificacion")
public class TipoIdentificacion implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long tipoIdentificacionId;
	
	@Size(max=200)
	@Column(nullable=true)
	private String nombre;
	
	@Size(max=200)
	@Column(nullable=true)
	private String descripcion;

	public Long getTipoIdentificacionId() {
		return tipoIdentificacionId;
	}

	public void setTipoIdentificacionId(Long tipoIdentificacionId) {
		this.tipoIdentificacionId = tipoIdentificacionId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
			
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}

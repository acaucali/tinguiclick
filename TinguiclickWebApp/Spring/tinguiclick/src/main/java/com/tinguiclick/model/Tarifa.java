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
@Table(name="tarifa")
public class Tarifa implements Serializable{
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long tarifaId;
	
	@Size(max=250)
	@Column(nullable=true)
	private String ubicacion;
	
	@Size(max=250)
	@Column(nullable=true)
	private String valor;
		
			
	public Long getTarifaId() {
		return tarifaId;
	}

	public void setTarifaId(Long tarifaId) {
		this.tarifaId = tarifaId;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
}

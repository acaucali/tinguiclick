package com.tinguiclick.pedidos.model;

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
@Table(name="factura")
public class Factura implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long facturaId;	
	
	@Column(nullable=true)
	private Date fechaRegistro;
	
	@Size(max=5)
	@Column(nullable=true)
	private String horaRegistro;
	
	@Column(nullable=true)
	private Long domiciliarioId;
	
	@Column(nullable=true)
	private Byte tipo;
	
	@Column(nullable=true)
	private Double valor;
	
	@Column(nullable=true)
	private Long aliadoId;
	
	@Column(nullable=true)
	private String ubicacion;
	
	@Size(max=500)
	@Column(nullable=true)
	private String observacion;
	
		
	public Long getFacturaId() {
		return facturaId;
	}

	public void setFacturaId(Long facturaId) {
		this.facturaId = facturaId;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getHoraRegistro() {
		return horaRegistro;
	}

	public void setHoraRegistro(String horaRegistro) {
		this.horaRegistro = horaRegistro;
	}
	
	public Long getDomiciliarioId() {
		return domiciliarioId;
	}

	public void setDomiciliarioId(Long domiciliarioId) {
		this.domiciliarioId = domiciliarioId;
	}

	public Byte getTipo() {
		return tipo;
	}

	public void setTipo(Byte tipo) {
		this.tipo = tipo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Long getAliadoId() {
		return aliadoId;
	}

	public void setAliadoId(Long aliadoId) {
		this.aliadoId = aliadoId;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}



	private static final long serialVersionUID = 1L;
}

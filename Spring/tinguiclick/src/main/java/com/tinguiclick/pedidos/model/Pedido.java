package com.tinguiclick.pedidos.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tinguiclick.model.Aliados;
import com.tinguiclick.model.Domiciliarios;

@Entity
@Table(name="pedido")
public class Pedido implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long pedidoId;	
	
	@Size(max=500)
	@Column(nullable=true)
	private String nombreCliente;
	
	@Size(max=500)
	@Column(nullable=true)
	private String apellidoCliente;
	
	@Size(max=500)
	@Column(nullable=true)
	private String direccionCliente;
	
	@Column(nullable=true)
	private Long numeroCelular;
	
	@Column(nullable=true)
	private Long telefono;
	
	@Size(max=300)
	@Column(nullable=true)
	private String municipio;
	
	@Size(max=500)
	@Column(nullable=true)
	private String ciudad;
	
	@Column(nullable=true)
	private Byte metodoPago;
	
	@Size(max=500)
	@Column(nullable=true)
	private String detalle;
	
	@Size(max=500)
	@Column(nullable=true)
	private String observacion;
	
	@Size(max=200)
	@Column(nullable=true)
	private String valor;
	
	@Size(max=100)
	@Column(nullable=true)
	private String alerta;
	
	@Column(nullable=true)
	private Byte estado;
	
	@Column(nullable=true)
	private Byte tipo;
	
	@Column(nullable=true)
	private Long tarifa;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "aliadoId", nullable = false)
	@JsonIgnoreProperties(value={ "hibernateLazyInitializer", "handler", "pedidos" }, allowSetters = true)
	private Aliados aliado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "domiciliarioId", nullable = false)
	@JsonIgnoreProperties(value={ "hibernateLazyInitializer", "handler", "pedidos" }, allowSetters = true)
	private Domiciliarios domiciliario;
		
	public Long getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(Long pedidoId) {
		this.pedidoId = pedidoId;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getApellidoCliente() {
		return apellidoCliente;
	}

	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}

	public String getDireccionCliente() {
		return direccionCliente;
	}

	public void setDireccionCliente(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}

	public Long getNumeroCelular() {
		return numeroCelular;
	}

	public void setNumeroCelular(Long numeroCelular) {
		this.numeroCelular = numeroCelular;
	}

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Byte getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(Byte metodoPago) {
		this.metodoPago = metodoPago;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getAlerta() {
		return alerta;
	}

	public void setAlerta(String alerta) {
		this.alerta = alerta;
	}

	public Byte getEstado() {
		return estado;
	}

	public void setEstado(Byte estado) {
		this.estado = estado;
	}

	public Byte getTipo() {
		return tipo;
	}

	public void setTipo(Byte tipo) {
		this.tipo = tipo;
	}

	public Long getTarifa() {
		return tarifa;
	}

	public void setTarifa(Long tarifa) {
		this.tarifa = tarifa;
	}

	public Aliados getAliado() {
		return aliado;
	}

	public void setAliado(Aliados aliado) {
		this.aliado = aliado;
	}

	public Domiciliarios getDomiciliario() {
		return domiciliario;
	}

	public void setDomiciliario(Domiciliarios domiciliario) {
		this.domiciliario = domiciliario;
	}



	private static final long serialVersionUID = 1L;
}



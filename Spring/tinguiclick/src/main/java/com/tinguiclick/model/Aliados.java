package com.tinguiclick.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tinguiclick.pedidos.model.Pedido;

@Entity
@Table(name="aliados")
public class Aliados implements Serializable{
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long aliadoId;
	
	@Size(max=250)
	@Column(nullable=true)
	private String razonSocial;
	
	@Size(max=250)
	@Column(nullable=true)
	private String nit;
	
	@Size(max=250)
	@Column(nullable=true)
	private String nombre;
	
	@Column(nullable=true)
	private Long telefono;
	
	@Size(max=250)
	@Column(nullable=true)
	private String cuentaNequi;
	
	@Size(max=250)
	@Column(nullable=true)
	private String cuentaDaviplata;
	
	@Size(max=250)
	@Column(nullable=true)
	private String cuentaBancaria;
	
	@Size(max=350)
	@Column(nullable=true)
	private String direccionFactura;
	
	@Size(max=350)
	@Column(nullable=true)
	private String emailFactura;
	
	@Size(max=500)
	@Column(nullable=true)
	private String categoriaPrincipal;
	
	@Size(max=500)
	@Column(nullable=true)
	private String categoriaSecundaria;	
			
	@Size(max=500)
	@Column(nullable=true)
	private String categoriaTerciaria;
	
	@Column(nullable=true)
	private Long documentoId;
	
	@Column(nullable=true)
	private byte tipoCuentaBancaria;
	
	@Column(nullable=true)
	private Integer nombreBanco;
	
	public Long getAliadoId() {
		return aliadoId;
	}

	public void setAliadoId(Long aliadoId) {
		this.aliadoId = aliadoId;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	public String getCuentaNequi() {
		return cuentaNequi;
	}

	public void setCuentaNequi(String cuentaNequi) {
		this.cuentaNequi = cuentaNequi;
	}

	public String getCuentaDaviplata() {
		return cuentaDaviplata;
	}

	public void setCuentaDaviplata(String cuentaDaviplata) {
		this.cuentaDaviplata = cuentaDaviplata;
	}

	public String getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	public String getDireccionFactura() {
		return direccionFactura;
	}

	public void setDireccionFactura(String direccionFactura) {
		this.direccionFactura = direccionFactura;
	}

	public String getEmailFactura() {
		return emailFactura;
	}

	public void setEmailFactura(String emailFactura) {
		this.emailFactura = emailFactura;
	}

	public String getCategoriaPrincipal() {
		return categoriaPrincipal;
	}

	public void setCategoriaPrincipal(String categoriaPrincipal) {
		this.categoriaPrincipal = categoriaPrincipal;
	}

	public String getCategoriaSecundaria() {
		return categoriaSecundaria;
	}

	public void setCategoriaSecundaria(String categoriaSecundaria) {
		this.categoriaSecundaria = categoriaSecundaria;
	}

	public String getCategoriaTerciaria() {
		return categoriaTerciaria;
	}

	public void setCategoriaTerciaria(String categoriaTerciaria) {
		this.categoriaTerciaria = categoriaTerciaria;
	}

	public Long getDocumentoId() {
		return documentoId;
	}

	public void setDocumentoId(Long documentoId) {
		this.documentoId = documentoId;
	}
	
	public byte getTipoCuentaBancaria() {
		return tipoCuentaBancaria;
	}

	public void setTipoCuentaBancaria(byte tipoCuentaBancaria) {
		this.tipoCuentaBancaria = tipoCuentaBancaria;
	}

	public Integer getNombreBanco() {
		return nombreBanco;
	}

	public void setNombreBanco(Integer nombreBanco) {
		this.nombreBanco = nombreBanco;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
}

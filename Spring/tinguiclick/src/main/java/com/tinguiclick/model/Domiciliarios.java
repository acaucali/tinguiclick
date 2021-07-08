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
@Table(name="domiciliarios")
public class Domiciliarios implements Serializable{
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long domiciliarioId;
	
	@Size(max=250)
	@Column(nullable=true)
	private String nombres;
	
	@Size(max=250)
	@Column(nullable=true)
	private String apellidos;
	
	@Column(nullable=true)
	private Long identificacion;
	
	@Column(nullable=true)
	private Byte tipoIdentificacion;
	
	@Size(max=250)
	@Column(nullable=true)
	private String eps;
	
	@Size(max=250)
	@Column(nullable=true)
	private String pension;
	
	@Size(max=250)
	@Column(nullable=true)
	private String arl;
	
	@Column(nullable=true)
	private Long telefono;
	
	@Size(max=350)
	@Column(nullable=true)
	private String direccionHogar;
	
	@Size(max=10)
	@Column(nullable=true)
	private String grupoSanguineo;
	
	@Size(max=150)
	@Column(nullable=true)
	private String pasaporte;	
		
	@Column(nullable=true)
	private Boolean arriendo;
	
	@Column(nullable=true)
	private Integer duracionArriendo;
	
	@Size(max=350)
	@Column(nullable=true)
	private String horarioDisponibilidad;
	
	@Size(max=350)
	@Column(nullable=true)
	private String diasDisponibilidad;
	
	@Column(nullable=true)
	private Long usuarioId;
	
	@Column(nullable=true)
	private Long documentoId;
	
	@Size(max=350)
	@Column(nullable=true)
	private String cuentaNequi;
	
	@Size(max=350)
	@Column(nullable=true)
	private String cuentaDaviplata;
	
	@Size(max=350)
	@Column(nullable=true)
	private String cuentaBancaria;
	
	@JsonIgnoreProperties(value ={ "hibernateLazyInitializer", "handler", "domiciliario" }, allowSetters = true)
	@OneToMany(cascade= CascadeType.ALL, mappedBy="domiciliario", fetch=FetchType.LAZY)
	private List<Pedido> pedidos;

	public Long getDomiciliarioId() {
		return domiciliarioId;
	}

	public void setDomiciliarioId(Long domiciliarioId) {
		this.domiciliarioId = domiciliarioId;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Long getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(Long identificacion) {
		this.identificacion = identificacion;
	}

	public Byte getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(Byte tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public String getEps() {
		return eps;
	}

	public void setEps(String eps) {
		this.eps = eps;
	}

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	public String getDireccionHogar() {
		return direccionHogar;
	}

	public void setDireccionHogar(String direccionHogar) {
		this.direccionHogar = direccionHogar;
	}

	public String getGrupoSanguineo() {
		return grupoSanguineo;
	}

	public void setGrupoSanguineo(String grupoSanguineo) {
		this.grupoSanguineo = grupoSanguineo;
	}

	public String getPasaporte() {
		return pasaporte;
	}

	public void setPasaporte(String pasaporte) {
		this.pasaporte = pasaporte;
	}

	public Boolean getArriendo() {
		return arriendo;
	}

	public void setArriendo(Boolean arriendo) {
		this.arriendo = arriendo;
	}

	public Integer getDuracionArriendo() {
		return duracionArriendo;
	}

	public void setDuracionArriendo(Integer duracionArriendo) {
		this.duracionArriendo = duracionArriendo;
	}
	
	public String getHorarioDisponibilidad() {
		return horarioDisponibilidad;
	}

	public void setHorarioDisponibilidad(String horarioDisponibilidad) {
		this.horarioDisponibilidad = horarioDisponibilidad;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getPension() {
		return pension;
	}

	public void setPension(String pension) {
		this.pension = pension;
	}

	public String getArl() {
		return arl;
	}

	public void setArl(String arl) {
		this.arl = arl;
	}

	public String getDiasDisponibilidad() {
		return diasDisponibilidad;
	}

	public void setDiasDisponibilidad(String diasDisponibilidad) {
		this.diasDisponibilidad = diasDisponibilidad;
	}
	
	public Long getDocumentoId() {
		return documentoId;
	}

	public void setDocumentoId(Long documentoId) {
		this.documentoId = documentoId;
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

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
}

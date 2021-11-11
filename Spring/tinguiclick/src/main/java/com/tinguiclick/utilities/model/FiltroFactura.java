package com.tinguiclick.utilities.model;

import java.io.Serializable;

public class FiltroFactura implements Serializable{
	
	String fechaInicial;
	String fechaFinal;
	
	
	public String getFechaInicial() {
		return fechaInicial;
	}
	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	public String getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	
	private static final long serialVersionUID = 1L;
	
}

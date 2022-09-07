package com.uce.edu.demo.repository.modelo;

import java.io.Serializable;

public class ProductoSencillo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoBarras;
	private Integer cantidad;

	public ProductoSencillo() {

	}

	public ProductoSencillo(String codigoBarras, Integer cantidad) {
		this.codigoBarras = codigoBarras;
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "ProductoSencillo [codigoBarras=" + codigoBarras + ", cantidad=" + cantidad + "]";
	}

	// Set y Get
	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

}

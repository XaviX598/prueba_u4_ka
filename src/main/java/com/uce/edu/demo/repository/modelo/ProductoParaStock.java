package com.uce.edu.demo.repository.modelo;

import java.io.Serializable;

public class ProductoParaStock implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoBarras;
	private String nombre;
	private Integer stock;

	public ProductoParaStock() {

	}

	public ProductoParaStock(String codigoBarras, String nombre, Integer stock) {
		this.codigoBarras = codigoBarras;
		this.nombre = nombre;
		
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "ProductoStock [codigoBarras=" + codigoBarras + ", nombre=" + nombre + ", stock=" + stock + "]";
	}

	// Set y Get
	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

}

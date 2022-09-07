package com.uce.edu.demo.service;

import java.util.List;

import com.uce.edu.demo.repository.modelo.ProductoSencillo;
import com.uce.edu.demo.repository.modelo.Venta;

public interface IVentaService {

	public void realizarVenta(List<ProductoSencillo> productos, String cedula, String numeroVenta);

	public List<Venta> buscarTodos();

	public void actualizar(Venta venta);

}

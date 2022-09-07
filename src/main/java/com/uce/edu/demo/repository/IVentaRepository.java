package com.uce.edu.demo.repository;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Venta;

public interface IVentaRepository {

	public void ingresar(Venta venta);

	public List<Venta> buscarTodos();

	public void actualizar(Venta venta);
}

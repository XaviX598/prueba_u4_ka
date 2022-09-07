package com.uce.edu.demo.repository;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Producto;

public interface IProductoRepository {

	public void ingresar(Producto producto);

	public void actualizar(Producto producto);

	public Producto buscarCodigoBarras(String codigo);

	public long buscarCantidad(String codigo);

	public Producto buscarStock(String codigo);

	public List<Producto> buscarTodos();

}

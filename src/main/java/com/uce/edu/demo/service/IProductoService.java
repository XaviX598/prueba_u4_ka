package com.uce.edu.demo.service;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoParaStock;

public interface IProductoService {

	public void ingresarProducto(Producto producto);

	public ProductoParaStock consultarStock(String codBarras);
	
	public List<Producto> buscarTodos();
	
	public Producto buscarCodigoBarras(String codigo);
	
	public void actualizar(Producto producto);

}

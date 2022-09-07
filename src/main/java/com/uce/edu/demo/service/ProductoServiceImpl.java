package com.uce.edu.demo.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IProductoRepository;
import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoParaStock;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	private IProductoRepository iProductoRepository;

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void ingresarProducto(Producto producto) {
		Long cant = this.iProductoRepository.buscarCantidad(producto.getCodigoBarras());

		if (cant == 0) {
			this.iProductoRepository.ingresar(producto);
		} else {
			Producto p = this.iProductoRepository.buscarCodigoBarras(producto.getCodigoBarras());
			p.setStock(producto.getStock() + p.getStock());
			this.iProductoRepository.actualizar(p);
		}

	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public ProductoParaStock consultarStock(String codBarras) {
		Producto p = this.iProductoRepository.buscarStock(codBarras);

		ProductoParaStock ps = new ProductoParaStock();
		ps.setCodigoBarras(codBarras);
		ps.setNombre(p.getNombre());
		ps.setStock(p.getStock());

		return ps;
	}

	@Override
	public List<Producto> buscarTodos() {
		// TODO Auto-generated method stub
		return this.iProductoRepository.buscarTodos();
	}

	@Override
	public Producto buscarCodigoBarras(String codigo) {
		// TODO Auto-generated method stub
		return this.iProductoRepository.buscarCodigoBarras(codigo);
	}

	@Override
	public void actualizar(Producto producto) {
		// TODO Auto-generated method stub
		this.iProductoRepository.actualizar(producto);
	}

}

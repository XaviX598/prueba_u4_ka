package com.uce.edu.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IProductoRepository;
import com.uce.edu.demo.repository.IVentaRepository;
import com.uce.edu.demo.repository.modelo.Detalle;
import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoSencillo;
import com.uce.edu.demo.repository.modelo.Venta;

@Service
public class VentaServiceImpl implements IVentaService {

	@Autowired
	private IVentaRepository ventaRepository;

	@Autowired
	private IProductoRepository productoRepository;

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void realizarVenta(List<ProductoSencillo> productos, String cedula, String numeroVenta) {
		Venta venta = new Venta();
		venta.setCedulaCliente(cedula);
		venta.setFecha(LocalDateTime.now());
		venta.setNumero(numeroVenta);

		List<Detalle> detalles = new ArrayList<Detalle>();
		BigDecimal totalVenta = BigDecimal.ZERO;

		for (ProductoSencillo ps : productos) {
			Producto p = this.productoRepository.buscarCodigoBarras(ps.getCodigoBarras());
			if (p.getStock() == 0) {
				throw new RuntimeException();
			}

			if (p.getStock() <= ps.getCantidad()) {
				ps.setCantidad(p.getStock());
			}

			Detalle detalle = new Detalle();
			detalle.setCantidad(ps.getCantidad());
			detalle.setPrecioUnitario(p.getPrecio());
			detalle.setProducto(p);
			detalle.setSubtotal(p.getPrecio().multiply(new BigDecimal(ps.getCantidad())));
			detalle.setVenta(venta);

			detalles.add(detalle);

			totalVenta = totalVenta.add(detalle.getSubtotal());
			p.setStock(p.getStock() - ps.getCantidad());
			this.productoRepository.actualizar(p);
		}

		venta.setDetalles(detalles);
		venta.setTotalVenta(totalVenta);

		this.ventaRepository.ingresar(venta);
	}

	@Override
	public List<Venta> buscarTodos() {
		// TODO Auto-generated method stub
		return this.ventaRepository.buscarTodos()
				;
	}

	@Override
	public void actualizar(Venta venta) {
		// TODO Auto-generated method stub
		this.ventaRepository.actualizar(venta);
	}

}

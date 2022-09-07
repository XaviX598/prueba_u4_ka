package com.uce.edu.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IDetalleRepository;
import com.uce.edu.demo.repository.modelo.Detalle;
import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ReporteVenta;

@Service
public class DetalleServiceImpl implements IDetalleService {

	@Autowired
	private IDetalleRepository iDetalleRepository;

	@Override
	@Transactional(value = TxType.REQUIRED)
	public List<ReporteVenta> generarReporteVentas(LocalDateTime fecha, String categoria, Integer cantidad) {
		List<ReporteVenta> reporteVentas = new ArrayList<>();

		List<Detalle> detalles = this.iDetalleRepository.buscarFecha(fecha);

		for (Detalle detalle : detalles) {

			ReporteVenta reporteVenta = new ReporteVenta();

			if (detalle.getCantidad() >= cantidad) {

				Producto p = detalle.getProducto();

				if (p.getCategoria().equals(categoria)) {
					reporteVenta.setCodBarras(p.getCodigoBarras());
					reporteVenta.setPrecioUnitario(p.getPrecio());
					reporteVenta.setSubtotal(detalle.getSubtotal());
					reporteVenta.setCantidad(cantidad);
					reporteVenta.setNombre(p.getNombre());
					reporteVentas.add(reporteVenta);
				}

			}

		}
		return reporteVentas;
	}

}

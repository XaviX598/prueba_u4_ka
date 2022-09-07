package com.uce.edu.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.uce.edu.demo.repository.modelo.ReporteVenta;

public interface IDetalleService {

	public List<ReporteVenta> generarReporteVentas(LocalDateTime fecha, String categoria, Integer cantidad);

}

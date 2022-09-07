package com.uce.edu.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.uce.edu.demo.repository.modelo.Detalle;

public interface IDetalleRepository {

	public List<Detalle> buscarFecha(LocalDateTime fecha);

}

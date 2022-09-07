package com.uce.edu.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Detalle;

@Transactional
@Repository
public class DetalleRepositoryImpl implements IDetalleRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Detalle> buscarFecha(LocalDateTime fecha) {
		Query myNamedQuery = this.entityManager.createNamedQuery("Detalle.buscarPorFechaDeVenta");
		myNamedQuery.setParameter("fecha", fecha);
		// Bajo demanda
		List<Detalle> detalles = myNamedQuery.getResultList();
		for (Detalle d : detalles) {
			d.getProducto().getCategoria();
		}
		return detalles;
	}

}

package com.uce.edu.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.Venta;

@Transactional
@Repository
public class VentaRepositoryImpl implements IVentaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void ingresar(Venta venta) {
		this.entityManager.persist(venta);
	}

	@Override
	public List<Venta> buscarTodos() {
		// TODO Auto-generated method stub
		TypedQuery<Venta> myQuery = this.entityManager.createQuery("SELECT v FROM Venta v", Venta.class);

		return myQuery.getResultList();
	}

	@Override
	public void actualizar(Venta venta) {
		// TODO Auto-generated method stub
		this.entityManager.merge(venta);
	}

}

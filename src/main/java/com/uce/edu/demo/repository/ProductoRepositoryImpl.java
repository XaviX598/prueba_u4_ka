package com.uce.edu.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Producto;

@Transactional
@Repository
public class ProductoRepositoryImpl implements IProductoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void ingresar(Producto producto) {
		this.entityManager.persist(producto);
	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void actualizar(Producto producto) {
		this.entityManager.merge(producto);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Producto buscarCodigoBarras(String codigo) {
		TypedQuery<Producto> myQuery = this.entityManager
				.createQuery("SELECT p FROM Producto p WHERE p.codigoBarras = :codBarras", Producto.class);
		myQuery.setParameter("codBarras", codigo);
		return myQuery.getSingleResult();
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public long buscarCantidad(String codigo) {
		Query myQuery = this.entityManager
				.createQuery("SELECT COUNT(p) FROM Producto p WHERE p.codigoBarras = :codBarras");
		myQuery.setParameter("codBarras", codigo);
		return (long) myQuery.getSingleResult();
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Producto buscarStock(String codigo) {
//		Query myNativeQuery = this.entityManager
//				.createNativeQuery("SELECT * FROM producto WHERE prod_codigo_barras = :codBarras", Producto.class);
//		myNativeQuery.setParameter("codBarras", codigo);
//		return (Producto) myNativeQuery.getSingleResult();

		CriteriaBuilder myBuilder = this.entityManager.getCriteriaBuilder();

		CriteriaQuery<Producto> myQuery = myBuilder.createQuery(Producto.class);

		Root<Producto> myTabla = myQuery.from(Producto.class);

		Predicate predCogidoBarras = myBuilder.equal(myTabla.get("codBarras"), codigo);

		Predicate miPredFinal = myBuilder.and(predCogidoBarras);

		CriteriaQuery<Producto> myQueryCompleto = myQuery.select(myTabla).where(miPredFinal);

		TypedQuery<Producto> myQueryFinal = this.entityManager.createQuery(myQueryCompleto);

		return myQueryFinal.getSingleResult();
	}

	@Override
	public List<Producto> buscarTodos() {
		// TODO Auto-generated method stub
		TypedQuery<Producto> myQuery = this.entityManager.createQuery("SELECT p FROM Producto p", Producto.class);

		return myQuery.getResultList();
	}

}

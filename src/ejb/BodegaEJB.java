package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entidad.Bodega;

@Stateless
public class BodegaEJB extends AbstractFacade<Bodega> {

	@PersistenceContext(unitName = "Practica_de_laboratorio_03_EJB_JSF_JPA")
	private EntityManager entityManager;

	public BodegaEJB() {
		super(Bodega.class);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return entityManager;
	}

	public Bodega buscarBodegaPorNombre(String nombre) {
		System.out.println("Bodega buscada" + nombre);
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Bodega> criteriaQuery = criteriaBuilder.createQuery(Bodega.class);
		Root<Bodega> categoriaRoot = criteriaQuery.from(Bodega.class);
		Predicate predicate = criteriaBuilder.equal(categoriaRoot.get("nombre"), nombre);
		criteriaQuery.select(categoriaRoot).where(predicate);

		return entityManager.createQuery(criteriaQuery).getSingleResult();
	}
	
}

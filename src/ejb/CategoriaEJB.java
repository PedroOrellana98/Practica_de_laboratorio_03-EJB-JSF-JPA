package ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entidad.Categoria;

@Stateless
public class CategoriaEJB extends AbstractFacade<Categoria> {

	@PersistenceContext(unitName = "Practica_de_laboratorio_03_EJB_JSF_JPA")
	private EntityManager entityManager;

	public CategoriaEJB() {
		super(Categoria.class);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public Categoria buscarCategoriaPorNombre(String nombre) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Categoria> criteriaQuery = criteriaBuilder.createQuery(Categoria.class);
		Root<Categoria> categoriaRoot = criteriaQuery.from(Categoria.class);
		Predicate predicate = criteriaBuilder.equal(categoriaRoot.get("nombre"), nombre);
		criteriaQuery.select(categoriaRoot).where(predicate);
		System.out.println("Salio de a buscar Categoria por nombre");
		return entityManager.createQuery(criteriaQuery).getSingleResult();
	}
	

	public Categoria getCategoryByName(String nombreCategoria) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Categoria> categoriaCriteriaQuery = criteriaBuilder.createQuery(Categoria.class);
		Root<Categoria> categoriaRoot = categoriaCriteriaQuery.from(Categoria.class);

		categoriaCriteriaQuery = categoriaCriteriaQuery.select(categoriaRoot)
				.where(criteriaBuilder.equal(categoriaRoot.get("nombre"), nombreCategoria));
		return entityManager.createQuery(categoriaCriteriaQuery).getSingleResult();
	}
	
	public List<Categoria> categoriaList(){
        return null;
    }

}

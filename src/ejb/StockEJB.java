package ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entidad.Bodega;
import entidad.Producto;
import entidad.Stock;

@Stateless
public class StockEJB extends AbstractFacade<Stock>{
	
	@PersistenceContext(unitName = "Practica_de_laboratorio_03_EJB_JSF_JPA")
    private EntityManager entityManager;
	public StockEJB() {
		super(Stock.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return entityManager;
	}
	
	  public Stock recuperarStock(Producto producto, Bodega bodega){
	        CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();
	        CriteriaQuery<Stock> criteriaQuery= criteriaBuilder.createQuery(Stock.class);
	        Root<Stock> usuarioRoot= criteriaQuery.from(Stock.class);
	        Predicate predicate= criteriaBuilder.equal(usuarioRoot.get("producto"),producto);
	        Predicate predicate1= criteriaBuilder.equal(usuarioRoot.get("bodega"),bodega);
	        Predicate validaciones= criteriaBuilder.and(predicate,predicate1);
	        criteriaQuery.select(usuarioRoot).where(validaciones);
	        return entityManager.createQuery(criteriaQuery).getSingleResult();

	    }
	    public List<Stock> recuperarStockPorBodega(Bodega bodega){
	    	
	        CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();
	        CriteriaQuery<Stock> criteriaQuery= criteriaBuilder.createQuery(Stock.class);
	        Root<Stock> usuarioRoot= criteriaQuery.from(Stock.class);
	        Predicate predicate= criteriaBuilder.equal(usuarioRoot.get("bodega"),bodega);
	        criteriaQuery.select(usuarioRoot).where(predicate);
	        return entityManager.createQuery(criteriaQuery).getResultList();

	    }

	
}

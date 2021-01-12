package ejb;

import java.util.*;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entidad.Categoria;
import entidad.Producto;

@Stateless
public class ProductoEJB extends AbstractFacade<Producto>{

	@PersistenceContext(unitName = "Practica_de_laboratorio_03_EJB_JSF_JPA")
    private EntityManager entityManager;
	public ProductoEJB() {
		super(Producto.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return entityManager;
	}
	
	public static List<Integer> codigoProductos;
	public static List<Producto> productos;
	public static List<Categoria> categoriasList;
	
	
	 public Producto buscarProducto(String nombre){

	        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	        CriteriaQuery<Producto> criteriaQuery = criteriaBuilder.createQuery(Producto.class);
	        Root<Producto> usuarioRoot=criteriaQuery.from(Producto.class);
	        Predicate predicate = criteriaBuilder.equal(usuarioRoot.get("nombre"),nombre);
	        criteriaQuery.select(usuarioRoot).where(predicate);
	        return entityManager.createQuery(criteriaQuery).getSingleResult();
	    }

	    public Producto buscarPrductoPorNombre(String nombre){
	        CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();
	        CriteriaQuery<Producto> criteriaQuery= criteriaBuilder.createQuery(Producto.class);
	        Root<Producto> categoriaRoot= criteriaQuery.from(Producto.class);
	        Predicate predicate= criteriaBuilder.equal(categoriaRoot.get("nombre"),nombre);
	        criteriaQuery.select(categoriaRoot).where(predicate);

	        return entityManager.createQuery(criteriaQuery).getSingleResult();
	    }
	    
	    public Producto buscarProductoPorCodigo(String id){
	        CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();
	        CriteriaQuery<Producto> criteriaQuery= criteriaBuilder.createQuery(Producto.class);
	        Root<Producto> categoriaRoot= criteriaQuery.from(Producto.class);
	        Predicate predicate= criteriaBuilder.equal(categoriaRoot.get("codigo"),id);
	        criteriaQuery.select(categoriaRoot).where(predicate);
	        return entityManager.createQuery(criteriaQuery).getSingleResult();
	    }

}

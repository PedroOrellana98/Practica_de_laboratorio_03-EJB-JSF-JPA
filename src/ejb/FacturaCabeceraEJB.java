package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entidad.FacturaCabecera;
import entidad.Pedido;

import java.util.List;

@Stateless
public class FacturaCabeceraEJB extends AbstractFacade<FacturaCabecera>{

	@PersistenceContext(unitName = "Practica_de_laboratorio_03_EJB_JSF_JPA")
    private EntityManager entityManager;
	public FacturaCabeceraEJB() {
		super(FacturaCabecera.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return entityManager;
	}
	
	  public FacturaCabecera getPedidoFacturaCabecera(Pedido pedido){
	        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	        CriteriaQuery<FacturaCabecera> criteriaQuery = criteriaBuilder.createQuery(FacturaCabecera.class);
	        Root<FacturaCabecera> FacturaCabeceraRoot = criteriaQuery.from(FacturaCabecera.class);
	        Predicate predicatePersona = criteriaBuilder.equal(FacturaCabeceraRoot.get("pedido"), pedido);
	        criteriaQuery.select(FacturaCabeceraRoot).where(predicatePersona);
	        return entityManager.createQuery(criteriaQuery).getSingleResult();
	    }

}

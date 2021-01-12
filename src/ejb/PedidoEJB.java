package ejb;

import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entidad.Pedido;
import entidad.Persona;

@Stateless
public class PedidoEJB extends AbstractFacade<Pedido>{

	@PersistenceContext(unitName = "Practica_de_laboratorio_03_EJB_JSF_JPA")
    private EntityManager entityManager;
	public PedidoEJB() {
		super(Pedido.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return entityManager;
	}
	
	 public Pedido getUltimoPedido(Persona persona, GregorianCalendar date){

	        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
	        Root<Pedido> pedidoRoot = criteriaQuery.from(Pedido.class);
	        Predicate predicatePersona = criteriaBuilder.equal(pedidoRoot.get("persona"), persona);
	        Predicate predicateFecha = criteriaBuilder.equal(pedidoRoot.get("fecha_emision"), date);
	        Predicate[] predicates = new Predicate[]{predicatePersona, predicateFecha};

	        criteriaQuery.select(pedidoRoot).where(predicates);
	        List<Pedido> pedidoList = entityManager.createQuery(criteriaQuery).getResultList();

	        return pedidoList.get(pedidoList.size()-1);
	    }
	    //SCORPION CODE
	    public Pedido getCurrentPedido(Persona persona){
	        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
	        Root<Pedido> pedidoRoot = criteriaQuery.from(Pedido.class);
	        Predicate predicatePersona = criteriaBuilder.equal(pedidoRoot.get("persona"), persona);
	        Predicate[] predicates = new Predicate[]{predicatePersona};
	        criteriaQuery.select(pedidoRoot).where(predicates);
	        List<Pedido> pedidoList = entityManager.createQuery(criteriaQuery).getResultList();
	        return pedidoList.get(pedidoList.size()-1);

	    }

	    public List<Pedido> findByPedidosId(Persona persona) {
	        // TODO Auto-generated method stub
	        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
	        Root<Pedido> pedidoRoot = criteriaQuery.from(Pedido.class);
	        Predicate predicatePersona = criteriaBuilder.equal(pedidoRoot.get("persona"), persona);
	        criteriaQuery.select(pedidoRoot).where(predicatePersona);
	        return entityManager.createQuery(criteriaQuery).getResultList();

	    }
	

}

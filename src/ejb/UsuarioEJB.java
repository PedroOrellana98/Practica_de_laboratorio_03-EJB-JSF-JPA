package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entidad.Usuario;

@Stateless
public class UsuarioEJB extends AbstractFacade<Usuario>{

	@PersistenceContext(unitName = "Practica_de_laboratorio_03_EJB_JSF_JPA")
    private EntityManager entityManager;
	public UsuarioEJB() {
		super(Usuario.class);
		// TODO Auto-generated constructor stub
	}
	
	public synchronized Usuario logIn(String correo, String password){
        CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteriaQuery= criteriaBuilder.createQuery(Usuario.class);
        Root<Usuario> usuarioRoot= criteriaQuery.from(Usuario.class);
        Predicate predicate= criteriaBuilder.equal(usuarioRoot.get("correo"),correo);
        Predicate predicate1= criteriaBuilder.equal(usuarioRoot.get("password"),password);
        Predicate validaciones= criteriaBuilder.and(predicate,predicate1);
        criteriaQuery.select(usuarioRoot).where(validaciones);
        try {
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        }catch (Exception e){
            return null;
        }
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return entityManager;
	}

}

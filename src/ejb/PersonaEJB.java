package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entidad.Persona;

@Stateless
public class PersonaEJB extends AbstractFacade<Persona> {

	@PersistenceContext(unitName = "Practica_de_laboratorio_03_EJB_JSF_JPA")
	private EntityManager entityManager;

	public PersonaEJB() {
		super(Persona.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return entityManager;
	}

	public boolean verificarUsuario(String cedula, String password) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Persona> usuarioCriteriaQuery = criteriaBuilder.createQuery(Persona.class);
		Root<Persona> usuarioRoot = usuarioCriteriaQuery.from(Persona.class);

		Predicate[] predicates = new Predicate[] { criteriaBuilder.equal(usuarioRoot.get("cedula"), cedula),
				criteriaBuilder.equal(usuarioRoot.get("password"), password),
				criteriaBuilder.equal(usuarioRoot.get("anulado"), 'F') };

		usuarioCriteriaQuery.select(usuarioRoot).where(predicates);
		try {
			Persona persona = getEntityManager().createQuery(usuarioCriteriaQuery).getSingleResult();
			return persona != null;
		} catch (Exception e) {
			return false;
		}
	}

	public Persona searchPerson(String cedula) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Persona> usuarioCriteriaQuery = criteriaBuilder.createQuery(Persona.class);
		Root<Persona> usuarioRoot = usuarioCriteriaQuery.from(Persona.class);
		Predicate predicate = criteriaBuilder.equal(usuarioRoot.get("cedula"), cedula);
		usuarioCriteriaQuery.select(usuarioRoot).where(predicate);
		return entityManager.createQuery(usuarioCriteriaQuery).getSingleResult();
	}
}

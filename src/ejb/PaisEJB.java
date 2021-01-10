package ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidad.Pais;

@Stateless
public class PaisEJB extends AbstractFacade<Pais> {

	@PersistenceContext(unitName = "Practica_de_laboratorio_03_EJB_JSF_JPA")
	private EntityManager entityManager;

	public PaisEJB() {
		super(Pais.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return entityManager;
	}

	public Pais buscarPais(String nombre) {
		String query = "SELECT * FROM pais WHERE nombre='" + nombre + "';";
		return (Pais) getEntityManager().createQuery(query).getSingleResult();
	}

}

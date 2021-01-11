package ejb;

import java.util.ArrayList;
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

	/*public List<Pais> buscarCodigoPais(String nombre) {
		List<Pais> paises = new ArrayList<Pais>();
		String consulta = "Select p From pais p Where p.nombre=:nombre";
		System.out.println("Se lanzo la siguiente consulta: " + consulta);
		try {
			paises = entityManager.createQuery(consulta).setParameter("nombre", nombre).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Existio un error en la consulta");
		}
		return paises;
	}*/
}

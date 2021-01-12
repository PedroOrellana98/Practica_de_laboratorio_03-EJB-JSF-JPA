package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidad.Rol;

@Stateless
public class RolEJB extends AbstractFacade<Rol> {

	@PersistenceContext(unitName = "Practica_de_laboratorio_03_EJB_JSF_JPA")
    private EntityManager entityManager;

    public RolEJB() {
        super(Rol.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
    
}

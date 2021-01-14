package controlador;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ejb.PaisEJB;
import entidad.Pais;

@Named
@SessionScoped
public class PaisControlador implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private PaisEJB paisEJB;

	private String nombre;

	public PaisControlador() {
	}

	@PostConstruct
	public void init() {

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Pais consultarPais(String nombre){
        Pais p;
        
        p=paisEJB.find(nombre);
        if (p==null){
        	paisEJB.create(new Pais(nombre,nombre));
            return paisEJB.find(nombre);
        }
        return new Pais("", "");
    }

}

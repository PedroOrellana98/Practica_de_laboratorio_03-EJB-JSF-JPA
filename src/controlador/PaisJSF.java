package controlador;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ejb.PaisEJB;
import entidad.Pais;

@Named
@SessionScoped
public class PaisJSF implements Serializable{

	@EJB
	private PaisEJB paisejb;

	public Pais crearPais(String nombrePais){
        Pais pais;
        pais = paisejb.find(nombrePais);
        if (pais==null){
            paisejb.create(new Pais("2", nombrePais));
            System.out.println("Se creo el pais: " + nombrePais);
            return paisejb.find(nombrePais);
        }
        return new Pais("", "");
    }
	
}

package controlador;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ejb.PaisEJB;
import entidad.Pais;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class PaisJSF implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nombre;
	@EJB
	private PaisEJB paisejb;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String crearPais() {
		Pais p = new Pais();
		if (p.equals(null)) {
			System.out.println("Ya se ha ingresado en la base");
		}else{
			p.setCodigo("2");
			p.setNombre(nombre);
			paisejb.create(p);
			System.out.println("Se creo el pais" + p);
		}
		return "pais";
	}
    
    public List<Pais> listarPaises() {
    	return paisejb.findAll();
    }
    
    public String buscarCodigoPais(String buscar){
    	return paisejb.find(buscar).getCodigo();
    }
    
}

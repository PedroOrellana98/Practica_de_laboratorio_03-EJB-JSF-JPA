package controlador;

import ejb.BodegaEJB;
import ejb.CiudadEJB;
import ejb.PaisEJB;
import ejb.ProvinciaEJB;
import entidad.Bodega;
import entidad.Ciudad;
import entidad.Pais;
import entidad.Provincia;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;

import javax.inject.Named;
import java.io.Serializable;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class BodegaControlador implements Serializable {
	private static final long serialVersionUID = 1L;
	private String ciudad;
	private String provincia;
	private String pais;
	private String nombre;
	@EJB
	private BodegaEJB bodegaEJB;
	private PaisEJB paisEJB;
	private CiudadEJB ciudadEJB;
	private ProvinciaEJB provinciaEJB;

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BodegaEJB getBodegaEJB() {
		return bodegaEJB;
	}

	public void setBodegaEJB(BodegaEJB bodegaEJB) {
		this.bodegaEJB = bodegaEJB;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String crearPais() {

		Pais p = new Pais();
		if (p.equals(null)) {
			System.out.println("Ya se ha ingresado en la base");
		} else {
			p.setCodigo("3");
			p.setNombre(pais);
			paisEJB.create(p);
			System.out.println("Se creo el pais" + p);
		}
		return "pais";
	}

	public String crearCiudad() {

		Ciudad c = new Ciudad();
		if (c.equals(null)) {
			System.out.println("Ya se ha ingresado en la base");
		} else {
			c.setCodigo("3");
			c.setNombre(ciudad);
			ciudadEJB.create(c);
			System.out.println("Se creo la ciudad" + c);
		}
		return "pais";
	}

	public String crearProvincia() {

		Pais p = new Pais();
		if (p.equals(null)) {
			System.out.println("Ya se ha ingresado en la base");
		} else {
			p.setCodigo("3");
			p.setNombre(pais);
			paisEJB.create(p);
			System.out.println("Se creo el pais" + p);
		}
		return "pais";
	}
	
	public void CrearBodegas() {
		
		String codigo = "3";
		Pais p = new Pais();
		Ciudad c = new Ciudad();
		Provincia pr = new Provincia();
		Bodega b = new Bodega();
		if (p.equals(null) && c.equals(null)) {
			System.out.println("Ya se ha ingresado en la base");
		} else {
			p.setCodigo(codigo);
			p.setNombre(pais);
			c.setCodigo(codigo);
			c.setNombre(ciudad);
			pr.setCodigo(codigo);
			pr.setNombre(provincia);
			b.setCodigo(3);
			b.setNombre(nombre);
			paisEJB.create(p);
			ciudadEJB.create(c);
			provinciaEJB.create(pr);
			bodegaEJB.create(b);
			System.out.println("Se creo el pais" + p);
			System.out.println("Se creo la provincia" + pr);
			System.out.println("Se creo la ciudad" + c);
			System.out.println("Se creo la bodega" + b);
		}
		
	}
}

package controlador;

import ejb.BodegaEJB;
import ejb.CiudadEJB;
import ejb.PaisEJB;
import ejb.ProvinciaEJB;
import entidad.Bodega;
import entidad.Ciudad;
import entidad.Pais;
import entidad.Provincia;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class BodegaControlador implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private BodegaEJB bodegaEJB;
	@EJB
	private PaisEJB paisEJB;
	@EJB
	private ProvinciaEJB provinciaEJB;
	@EJB
	private CiudadEJB ciudadEJB;
	private List<Bodega> bodegas;
	private String nombre;
	private String nombreBodega;
	private int idBodega;
	private Bodega bodega;
	private String cookie;

	private String level1, level2, level3;
	private boolean level2ListDisabled = true, level3ListDisabled = true;
	private Map<String, String> paises;
	private String codePais;
	private String apiKey = "81e5561086815798d63d81a2b4b2339e";

	public BodegaControlador() {

	}

	@PostConstruct
	public void init() {
		this.bodegas = this.bodegaEJB.findAll();
	}

	public Bodega[] getBodegas() {
		return bodegas.toArray(new Bodega[0]);
	}

	public void setBodegas(List<Bodega> bodegas) {
		this.bodegas = bodegas;
	}

	public String getnombre() {
		return nombre;
	}

	public void setnombre(String nombre) {
		this.nombre = nombre;
	}
	
	public boolean isLevel2ListDisabled() {
		return level2ListDisabled;
	}

	public void setLevel2ListDisabled(boolean level2ListDisabled) {
		this.level2ListDisabled = level2ListDisabled;
	}

	public boolean isLevel3ListDisabled() {
		return level3ListDisabled;
	}

	public void setLevel3ListDisabled(boolean level3ListDisabled) {
		this.level3ListDisabled = level3ListDisabled;
	}

	public String getLevel1() {
		return level1;
	}

	public String getLevel2() {
		return level2;
	}

	public String getLevel3() {
		return level3;
	}

	public void setLevel1(String level1) {
		this.level1 = level1;
		this.setLevel2("Seleccionar");
		this.level2ListDisabled = this.level1.equals("Seleccionar");
	}

	public void setLevel2(String level2) {
		this.level2 = level2;
		this.setLevel3("Seleccionar");
		this.level3ListDisabled = this.level2.equals("Seleccionar");
	}

	public void setLevel3(String level3) {
		this.level3 = level3;
	}
	
	public String getNombreBodega() {
		return nombreBodega;
	}

	public void setNombreBodega(String nombreBodega) {
		this.nombreBodega = nombreBodega;
	}

	public int getIdBodega() {
		return idBodega;
	}

	public void setIdBodega(int idBodega) {
		this.idBodega = idBodega;
	}

	public String add() {
		String[] paisProCiu = this.level3.split("-");
		Pais pais = paisEJB.find("Ecuador");
		Provincia provincia = consultarProvincia(paisProCiu[1].toUpperCase(), pais);
		Ciudad ciudad = consultarCiudad(paisProCiu[2].toUpperCase(), provincia);
		this.bodegaEJB.create(new Bodega(this.nombre.toUpperCase(), ciudad));
		
		this.nombre = "";
		this.level1 = "Seleccionar";
		this.level3 = "Seleccionar";
		this.bodegas = this.bodegaEJB.findAll();
		return null;
	}

	public Pais consultarPais(String nombre) {
		Pais p;
		p = paisEJB.find(nombre);
		if (p == null) {
			paisEJB.create(new Pais(nombre, nombre));
			System.out.println("Crear PAIS" + nombre);
			return paisEJB.find(nombre);
		}
		return new Pais("", "");
	}

	public Provincia consultarProvincia(String nombre, Pais pais) {
		Provincia p = provinciaEJB.find(nombre);
		if (p == null) {
			provinciaEJB.create(new Provincia(nombre, nombre, pais));
			return provinciaEJB.find(nombre);
		}
		return p;
	}

	public Ciudad consultarCiudad(String nombre, Provincia provincia) {
		Ciudad c = ciudadEJB.find(nombre);
		if (c == null) {
			ciudadEJB.create(new Ciudad(nombre, nombre, provincia));
			return ciudadEJB.find(nombre);
		}
		return c;
	}

	public void buscarBodega(int codigo) {
		this.bodega = bodegaEJB.find(codigo);
		this.nombreBodega = bodega.getNombre();
		this.idBodega = bodega.getCodigo();
	}

	public void actualizarBodega() {
		String[] paisProCiu = this.level3.split("-");
		Pais pais = paisEJB.find("Ecuador");
		Provincia provincia = consultarProvincia(paisProCiu[1].toUpperCase(), pais);
		Ciudad ciudad = consultarCiudad(paisProCiu[2].toUpperCase(), provincia);
		this.bodega.setCiudad(ciudad);
		this.bodega.setNombre(nombreBodega.toUpperCase());
		this.bodegaEJB.edit(this.bodega);
		this.bodegas = null;
		this.bodegas = bodegaEJB.findAll();
		this.level1 = "Seleccionar";
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
				.handleNavigation(FacesContext.getCurrentInstance(), null, "paginaBodega.xhtml");
	}

	public void navegar() {
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
				.handleNavigation(FacesContext.getCurrentInstance(), null, "paginaBodegaActualizacion.xhtml");
	}

	public String deleted(Bodega b) {
		bodegaEJB.remove(b);
		bodegas = bodegaEJB.findAll();
		return null;
	}

	public String edit(Bodega b) {
		b.setEditable(true);
		return null;
	}

	public String save(Bodega b) {
		this.bodegaEJB.edit(b);
		b.setEditable(false);
		return null;
	}

	private ArrayList<String> generateList(String pre, int size) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Seleccionar");

		return list;
	}

	private ArrayList<String> obtenerPaises() {
		ArrayList<String> paisesf = new ArrayList<String>();
		paisesf.add("Pais");

		HttpClient cliente = new HttpClient(new OnHttpRequestComplete() {
			@Override
			public void onComplete(Response status) {
				if (status.isSuccess()) {

					String arreglo = status.getResult();

					arreglo = arreglo.substring(0, arreglo.length() - 1);
					String[] p = arreglo.split("}");

					paises = new HashMap<>();
					for (String pp : p) {
						pp = pp.substring(9, pp.length());

						String[] pp2 = pp.split(",");
						String key = pp2[0].substring(1, pp2[0].length() - 1);
						String value = pp2[1].substring(pp2[1].length() - 3, pp2[1].length() - 1);

						paises.put(key, value);
						paisesf.add(key);
					}
				}
			}
		});

		cliente.excecute("http://battuta.medunes.net/api/country/all/?key=" + apiKey);
		return paisesf;
	}

	private ArrayList<String> obtenerProvincias(String pais) {

		ArrayList<String> provincias = new ArrayList<String>();
		provincias.add("Provincia");
		HttpClient cliente = new HttpClient(new OnHttpRequestComplete() {
			@Override
			public void onComplete(Response status) {
				if (status.isSuccess()) {

					String arreglo = status.getResult();

					arreglo = arreglo.substring(0, arreglo.length() - 1);
					String[] p = arreglo.split("}");

					for (String pp : p) {
						pp = pp.substring(11, pp.length());

						String[] pp2 = pp.split(",");
						String key = pp2[0].substring(1, pp2[0].length() - 1);
						provincias.add(pais + "-" + key);

					}
				}
			}
		});
		this.codePais = paises.get(pais);
		cliente.excecute("http://battuta.medunes.net/api/region/" + this.codePais + "/all/?key=" + apiKey);
		return provincias;
	}

	private ArrayList<String> obtenerCiudades(String provincia) {

		ArrayList<String> ciudades = new ArrayList<String>();
		ciudades.add("Ciudad");
		HttpClient cliente = new HttpClient(new OnHttpRequestComplete() {
			@Override
			public void onComplete(Response status) {
				if (status.isSuccess()) {

					String arreglo = status.getResult();

					arreglo = arreglo.substring(0, arreglo.length() - 1);
					String[] p = arreglo.split("}");

					for (String pp : p) {
						pp = pp.substring(9, pp.length());

						String[] pp2 = pp.split(",");
						String key = pp2[0].substring(1, pp2[0].length() - 1);
						ciudades.add(provincia + "-" + key);
					}
				}
			}
		});
		String[] provinciaF = provincia.split(" ");

		cliente.excecute("http://battuta.medunes.net/api/city/" + this.codePais + "/search/?region="
				+ provinciaF[provinciaF.length - 1] + "&key=" + apiKey);
		return ciudades;
	}

	public ArrayList<String> getLevel1List() {

		return obtenerPaises();
	}

	public ArrayList<String> getLevel2List() {
		if (this.level2ListDisabled)
			return this.generateList("", 1);
		else
			return this.obtenerProvincias(this.level1);
	}

	public ArrayList<String> getLevel3List() {
		if (this.level3ListDisabled)
			return this.generateList("", 1);
		else
			return this.obtenerCiudades(this.level2);
	}

	public String getCookie() {
		Cookie cookie = (Cookie) FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap()
				.get("administrador");
		if (cookie == null || cookie.getValue().equals("")) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("../Public/paginaCatalogo.xhtml");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "Bienvenido!";
	}

	public void regresar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("../Private/paginaAdmin.xhtml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

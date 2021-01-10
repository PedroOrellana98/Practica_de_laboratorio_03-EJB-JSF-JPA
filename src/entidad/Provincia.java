package entidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Provincia
 *
 */
@Entity

public class Provincia implements Serializable {
	
	private static final long serialVersionUID = 1L;

	 @Id
	 private String codigo;
	 private String nombre;

	 @ManyToOne
	 @JoinColumn
	 private Pais pais;

	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "provincia")
	 private List<Ciudad> listaCiudades;

	 public Provincia(){}

	 public Provincia(String codigo,String nombre,Pais pais) {
		 
	     this.nombre = nombre;
	     this.codigo = codigo;
	     this.pais = pais;
	 }

	 public Provincia(String codigo, String nombre, Pais pais, Object o) {
		 
	 }

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public List<Ciudad> getListaCiudades() {
		return listaCiudades;
	}

	public void setListaCiudades(List<Ciudad> listaCiudades) {
		this.listaCiudades = listaCiudades;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((listaCiudades == null) ? 0 : listaCiudades.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Provincia other = (Provincia) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (listaCiudades == null) {
			if (other.listaCiudades != null)
				return false;
		} else if (!listaCiudades.equals(other.listaCiudades))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Provincia [codigo=" + codigo + ", nombre=" + nombre + ", pais=" + pais + ", listaCiudades="
				+ listaCiudades + "]";
	}
	 
}

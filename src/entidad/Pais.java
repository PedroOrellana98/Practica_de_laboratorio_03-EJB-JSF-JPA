package entidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Pais
 *
 */
@Entity

public class Pais implements Serializable {
	
	private static final long serialVersionUID = 1L;

	 @Id
	 private String codigo;
	 private String nombre;

	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "pais")
	 private List<Provincia> listaProvincias;


	 public Pais(){
		 
	 }

	 public Pais(String codigo,String nombre) {
	      this.codigo=codigo;
	      this.nombre = nombre;
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

	public List<Provincia> getListaProvincias() {
		return listaProvincias;
	}

	public void setListaProvincias(List<Provincia> listaProvincias) {
		this.listaProvincias = listaProvincias;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((listaProvincias == null) ? 0 : listaProvincias.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Pais other = (Pais) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (listaProvincias == null) {
			if (other.listaProvincias != null)
				return false;
		} else if (!listaProvincias.equals(other.listaProvincias))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pais [codigo=" + codigo + ", nombre=" + nombre + ", listaProvincias=" + listaProvincias + "]";
	}
	
}

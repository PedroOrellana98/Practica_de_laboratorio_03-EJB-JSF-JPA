package entidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Rol
 *
 */
@Entity

public class Rol implements Serializable {
	
	private static final long serialVersionUID = 1L;

	 @Id
	 private String nombre;
	 private String descripcion;

	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "rol")
	 private List<Usuario> usuariosRolesList;

	 public Rol(){}

	 public Rol(String nombre, String descripcion, List<Usuario> usuariosRolesList) {
	     this.nombre = nombre;
	     this.descripcion = descripcion;
	     this.usuariosRolesList = usuariosRolesList;
	 }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Usuario> getUsuariosRolesList() {
		return usuariosRolesList;
	}

	public void setUsuariosRolesList(List<Usuario> usuariosRolesList) {
		this.usuariosRolesList = usuariosRolesList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((usuariosRolesList == null) ? 0 : usuariosRolesList.hashCode());
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
		Rol other = (Rol) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (usuariosRolesList == null) {
			if (other.usuariosRolesList != null)
				return false;
		} else if (!usuariosRolesList.equals(other.usuariosRolesList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rol [nombre=" + nombre + ", descripcion=" + descripcion + ", usuariosRolesList=" + usuariosRolesList
				+ "]";
	}
	 
}

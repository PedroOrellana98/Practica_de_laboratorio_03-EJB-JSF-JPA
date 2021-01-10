package entidad;

import java.io.Serializable;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Ciudad
 *
 */
@Entity

public class Ciudad implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    private String codigo;
    private String nombre;

    @ManyToOne
    private Provincia provincia;

    @JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ciudad")
    private List<Bodega> listaBodeas;

    public Ciudad(){

    }

    public Ciudad(String codigo, String nombre, Provincia provincia) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.provincia = provincia;
        /*this.listaBodeas = listaBodeas;*/
    }

    public Ciudad(String codigo, String nombre, Provincia provincia, List<Bodega> listaBodeas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.provincia = provincia;
        this.listaBodeas = listaBodeas;
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

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public List<Bodega> getListaBodeas() {
		return listaBodeas;
	}

	public void setListaBodeas(List<Bodega> listaBodeas) {
		this.listaBodeas = listaBodeas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((listaBodeas == null) ? 0 : listaBodeas.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((provincia == null) ? 0 : provincia.hashCode());
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
		Ciudad other = (Ciudad) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (listaBodeas == null) {
			if (other.listaBodeas != null)
				return false;
		} else if (!listaBodeas.equals(other.listaBodeas))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (provincia == null) {
			if (other.provincia != null)
				return false;
		} else if (!provincia.equals(other.provincia))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ciudad [codigo=" + codigo + ", nombre=" + nombre + ", provincia=" + provincia + ", listaBodeas="
				+ listaBodeas + "]";
	}
   
}

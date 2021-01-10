package entidad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Bodega
 *
 */
@Entity

public class Bodega implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigo;
    private String nombre;

    @ManyToOne
    private Ciudad ciudad;

    @JsonbTransient
    @ManyToMany(mappedBy = "bodegasList")
    @JoinColumn
    private List<Producto> productosList;
    @Transient
    private boolean editable;

    //@JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bodega")
    private List<Stock> lista_stock;
    
    public Bodega(){
        productosList= new ArrayList<Producto>();
    }

    public Bodega(String nombre, Ciudad ciudad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        productosList = new ArrayList<Producto>();
        lista_stock= new ArrayList<Stock>();
    }

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public List<Producto> getProductosList() {
		return productosList;
	}

	public void setProductosList(List<Producto> productosList) {
		this.productosList = productosList;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public List<Stock> getLista_stock() {
		return lista_stock;
	}

	public void setLista_stock(List<Stock> lista_stock) {
		this.lista_stock = lista_stock;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ciudad == null) ? 0 : ciudad.hashCode());
		result = prime * result + codigo;
		result = prime * result + (editable ? 1231 : 1237);
		result = prime * result + ((lista_stock == null) ? 0 : lista_stock.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((productosList == null) ? 0 : productosList.hashCode());
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
		Bodega other = (Bodega) obj;
		if (ciudad == null) {
			if (other.ciudad != null)
				return false;
		} else if (!ciudad.equals(other.ciudad))
			return false;
		if (codigo != other.codigo)
			return false;
		if (editable != other.editable)
			return false;
		if (lista_stock == null) {
			if (other.lista_stock != null)
				return false;
		} else if (!lista_stock.equals(other.lista_stock))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (productosList == null) {
			if (other.productosList != null)
				return false;
		} else if (!productosList.equals(other.productosList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bodega [codigo=" + codigo + ", nombre=" + nombre + ", ciudad=" + ciudad + ", productosList="
				+ productosList + ", editable=" + editable + ", lista_stock=" + lista_stock + "]";
	}
    
}

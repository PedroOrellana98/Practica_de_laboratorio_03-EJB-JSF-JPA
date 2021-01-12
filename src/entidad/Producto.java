package entidad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Producto
 *
 */
@Entity

public class Producto implements Serializable {

	
	private static final long serialVersionUID = 1L;

	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private int codigo;
	 private String nombre;
	 private String imagen;
	 private double precioCompra;
	 private double precioVenta;
	 private char iva;
	 private int stock;

	 @JsonbTransient
	 @ManyToMany(cascade = CascadeType.MERGE)
	 @JoinColumn
	 private List<Bodega> bodegasList;

	 @ManyToOne
	 private Categoria categoria;

	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
	 private List<FacturaDetalle> facturasDetallesList;

	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
	 private List<Stock> lista_stock;

	 public Producto(){
	     bodegasList= new ArrayList<Bodega>();
	     facturasDetallesList= new ArrayList<FacturaDetalle>();
	     lista_stock= new ArrayList<Stock>();
	 }

	 public Producto(String nombre, String imagen, double precioCompra, double precioVenta, char iva, int stock,Categoria categoria) {
	     this.nombre = nombre;
	     this.imagen = imagen;
	     this.precioCompra = precioCompra;
	     this.precioVenta = precioVenta;
	     this.iva = iva;
	     this.stock = stock;
	     this.categoria = categoria;
	     bodegasList= new ArrayList<Bodega>();
	     lista_stock= new ArrayList<Stock>();
	 }

	 public Producto(int codigo, String nombre, String imagen, double precioCompra, double precioVenta, char iva, int stock, List<Bodega> bodegasList, Categoria categoria, List<FacturaDetalle> facturasDetallesList) {
	     this.codigo = codigo;
	     this.nombre = nombre;
	     this.imagen = imagen;
	     this.precioCompra = precioCompra;
	     this.precioVenta = precioVenta;
	     this.iva = iva;
	     this.stock = stock;
	     this.bodegasList = bodegasList;
	     this.categoria = categoria;
	     this.facturasDetallesList = facturasDetallesList;
	 }

	 public Producto(String nombre, String imagen, double precioCompra, double precioVenta, char iva, int stock, List<Bodega> bodegasList, Categoria categoria, List<FacturaDetalle> facturasDetallesList) {
	     this.nombre = nombre;
	     this.imagen = imagen;
	     this.precioCompra = precioCompra;
	     this.precioVenta = precioVenta;
	     this.iva = iva;
	     this.stock = stock;
	     this.bodegasList = bodegasList;
	     this.categoria = categoria;
	     this.facturasDetallesList = facturasDetallesList;
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

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public char getIva() {
		return iva;
	}

	public void setIva(char iva) {
		this.iva = iva;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public List<Bodega> getBodegasList() {
		return bodegasList;
	}

	public void setBodegasList(List<Bodega> bodegasList) {
		this.bodegasList = bodegasList;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<FacturaDetalle> getFacturasDetallesList() {
		return facturasDetallesList;
	}

	public void setFacturasDetallesList(List<FacturaDetalle> facturasDetallesList) {
		this.facturasDetallesList = facturasDetallesList;
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
	
    public void addBodega(Bodega bodega){
        this.bodegasList.add(bodega);
    }

    public void addStock(Stock stock){
        this.lista_stock.add(stock);
    }
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bodegasList == null) ? 0 : bodegasList.hashCode());
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + codigo;
		result = prime * result + ((facturasDetallesList == null) ? 0 : facturasDetallesList.hashCode());
		result = prime * result + ((imagen == null) ? 0 : imagen.hashCode());
		result = prime * result + iva;
		result = prime * result + ((lista_stock == null) ? 0 : lista_stock.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precioCompra);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(precioVenta);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + stock;
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
		Producto other = (Producto) obj;
		if (bodegasList == null) {
			if (other.bodegasList != null)
				return false;
		} else if (!bodegasList.equals(other.bodegasList))
			return false;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (codigo != other.codigo)
			return false;
		if (facturasDetallesList == null) {
			if (other.facturasDetallesList != null)
				return false;
		} else if (!facturasDetallesList.equals(other.facturasDetallesList))
			return false;
		if (imagen == null) {
			if (other.imagen != null)
				return false;
		} else if (!imagen.equals(other.imagen))
			return false;
		if (iva != other.iva)
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
		if (Double.doubleToLongBits(precioCompra) != Double.doubleToLongBits(other.precioCompra))
			return false;
		if (Double.doubleToLongBits(precioVenta) != Double.doubleToLongBits(other.precioVenta))
			return false;
		if (stock != other.stock)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Producto [codigo=" + codigo + ", nombre=" + nombre + ", imagen=" + imagen + ", precioCompra="
				+ precioCompra + ", precioVenta=" + precioVenta + ", iva=" + iva + ", stock=" + stock + ", bodegasList="
				+ bodegasList + ", categoria=" + categoria + ", facturasDetallesList=" + facturasDetallesList
				+ ", lista_stock=" + lista_stock + "]";
	}
   
}

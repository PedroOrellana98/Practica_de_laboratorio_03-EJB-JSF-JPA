package entidad;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: FacturaCabecera
 *
 */
@Entity

public class FacturaCabecera implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigo;
    @Temporal(TemporalType.DATE)
    private GregorianCalendar fecha;
    private char anulado;
    private double descuento;
    private double subtotal;
    private double iva_total;
    private double total;
    @Transient
    private boolean editable;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facturaCabecera")
    private List<FacturaDetalle> listaFacturasDetalles;
    //PEDIDOS
    @OneToOne
    private Pedido pedido;
    @ManyToOne
    private Persona persona;


    public FacturaCabecera(){}

    public FacturaCabecera(GregorianCalendar fecha, char anulado, double descuento, double subtotal, double iva_total, double total, Persona persona) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.anulado = anulado;
        this.descuento = descuento;
        this.subtotal = subtotal;
        this.iva_total = iva_total;
        this.iva_total = iva_total;
        this.total = total;
        this.listaFacturasDetalles = listaFacturasDetalles;
        this.persona = persona;
    }

    public FacturaCabecera(GregorianCalendar fecha, char anulado, double descuento, double subtotal, double iva_total, double total, List<FacturaDetalle> listaFacturasDetalles, Persona persona, Pedido pedido) {
        this.fecha = fecha;
        this.anulado = anulado;
        this.descuento = descuento;
        this.subtotal = subtotal;
        this.iva_total = iva_total;
        this.total = total;
        this.listaFacturasDetalles = listaFacturasDetalles;
        this.persona = persona;
        this.pedido = pedido;
    }

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public GregorianCalendar getFecha() {
		return fecha;
	}

	public void setFecha(GregorianCalendar fecha) {
		this.fecha = fecha;
	}

	public char getAnulado() {
		return anulado;
	}

	public void setAnulado(char anulado) {
		this.anulado = anulado;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getIva_total() {
		return iva_total;
	}

	public void setIva_total(double iva_total) {
		this.iva_total = iva_total;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public List<FacturaDetalle> getListaFacturasDetalles() {
		return listaFacturasDetalles;
	}

	public void setListaFacturasDetalles(List<FacturaDetalle> listaFacturasDetalles) {
		this.listaFacturasDetalles = listaFacturasDetalles;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + anulado;
		result = prime * result + codigo;
		long temp;
		temp = Double.doubleToLongBits(descuento);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (editable ? 1231 : 1237);
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		temp = Double.doubleToLongBits(iva_total);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((listaFacturasDetalles == null) ? 0 : listaFacturasDetalles.hashCode());
		result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
		result = prime * result + ((persona == null) ? 0 : persona.hashCode());
		temp = Double.doubleToLongBits(subtotal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(total);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		FacturaCabecera other = (FacturaCabecera) obj;
		if (anulado != other.anulado)
			return false;
		if (codigo != other.codigo)
			return false;
		if (Double.doubleToLongBits(descuento) != Double.doubleToLongBits(other.descuento))
			return false;
		if (editable != other.editable)
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (Double.doubleToLongBits(iva_total) != Double.doubleToLongBits(other.iva_total))
			return false;
		if (listaFacturasDetalles == null) {
			if (other.listaFacturasDetalles != null)
				return false;
		} else if (!listaFacturasDetalles.equals(other.listaFacturasDetalles))
			return false;
		if (pedido == null) {
			if (other.pedido != null)
				return false;
		} else if (!pedido.equals(other.pedido))
			return false;
		if (persona == null) {
			if (other.persona != null)
				return false;
		} else if (!persona.equals(other.persona))
			return false;
		if (Double.doubleToLongBits(subtotal) != Double.doubleToLongBits(other.subtotal))
			return false;
		if (Double.doubleToLongBits(total) != Double.doubleToLongBits(other.total))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FacturaCabecera [codigo=" + codigo + ", fecha=" + fecha + ", anulado=" + anulado + ", descuento="
				+ descuento + ", subtotal=" + subtotal + ", iva_total=" + iva_total + ", total=" + total + ", editable="
				+ editable + ", listaFacturasDetalles=" + listaFacturasDetalles + ", pedido=" + pedido + ", persona="
				+ persona + "]";
	}
}

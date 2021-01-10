package entidad;

import java.io.Serializable;
import java.util.GregorianCalendar;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Pedido
 *
 */
@Entity

public class Pedido implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    private String estado;
    private GregorianCalendar fecha_emision;
    @ManyToOne
    private Persona persona;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "pedido")
    private FacturaCabecera facturaCabecera;
    @Transient
    private boolean editable;

    public Pedido() {

    }

    public Pedido(String estado, GregorianCalendar fecha_emision, Persona persona, FacturaCabecera facturaCabecera) {
        this.estado = estado;
        this.fecha_emision = fecha_emision;
        this.persona = persona;
        this.facturaCabecera = facturaCabecera;
    }

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public GregorianCalendar getFecha_emision() {
		return fecha_emision;
	}

	public void setFecha_emision(GregorianCalendar fecha_emision) {
		this.fecha_emision = fecha_emision;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public FacturaCabecera getFacturaCabecera() {
		return facturaCabecera;
	}

	public void setFacturaCabecera(FacturaCabecera facturaCabecera) {
		this.facturaCabecera = facturaCabecera;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + (editable ? 1231 : 1237);
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((facturaCabecera == null) ? 0 : facturaCabecera.hashCode());
		result = prime * result + ((fecha_emision == null) ? 0 : fecha_emision.hashCode());
		result = prime * result + ((persona == null) ? 0 : persona.hashCode());
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
		Pedido other = (Pedido) obj;
		if (codigo != other.codigo)
			return false;
		if (editable != other.editable)
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (facturaCabecera == null) {
			if (other.facturaCabecera != null)
				return false;
		} else if (!facturaCabecera.equals(other.facturaCabecera))
			return false;
		if (fecha_emision == null) {
			if (other.fecha_emision != null)
				return false;
		} else if (!fecha_emision.equals(other.fecha_emision))
			return false;
		if (persona == null) {
			if (other.persona != null)
				return false;
		} else if (!persona.equals(other.persona))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pedido [codigo=" + codigo + ", estado=" + estado + ", fecha_emision=" + fecha_emision + ", persona="
				+ persona + ", facturaCabecera=" + facturaCabecera + ", editable=" + editable + "]";
	}
    
}

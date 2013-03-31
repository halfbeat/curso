package practica1.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Entity;

@Entity
@Table(name = "T_ESTADO")
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "C_ESTADO_ID", length = 10)
	private String estadoId;
	@Column(name = "D_ESTADO", length = 20)
	private String estado;

	public static Estado EN_ESTUDIO = new Estado("EST", "EN ESTUDIO");
	public static Estado EN_DESARROLLO = new Estado("DES", "EN DESARROLLO");
	public static Estado ENTREGADO = new Estado("ENT", "ENTREGADO");
	public static Estado FINALIZADO = new Estado("FIN", "FINALIZADO");
	public static Estado CANCELADO = new Estado("CAN", "CANCELADO");

	public Estado() {
	}

	public Estado(String id, String nombre) {
		estadoId = id;
		estado = nombre;
	}

	public String getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((estadoId == null) ? 0 : estadoId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Estado other = (Estado) obj;
		if (estadoId == null) {
			if (other.estadoId != null) {
				return false;
			}
		} else if (!estadoId.equals(other.estadoId)) {
			return false;
		}
		return true;
	}
}

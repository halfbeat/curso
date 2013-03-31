package practica1.modelo;

import java.io.Serializable;

public class ProyectoDepartamentoPK implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long proyectoId;
	private Long empresaId;
	private Long departamentoId;

	public ProyectoDepartamentoPK() {
	}

	public ProyectoDepartamentoPK(Long proyectoId, Long empresaId,
			Long departamentoId) {
		this.proyectoId = proyectoId;
		this.empresaId = empresaId;
		this.departamentoId = departamentoId;
	}

	public Long getProyectoId() {
		return proyectoId;
	}

	public void setProyectoId(Long proyectoId) {
		this.proyectoId = proyectoId;
	}

	public Long getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(Long empresaId) {
		this.empresaId = empresaId;
	}

	public Long getDepartamentoId() {
		return departamentoId;
	}

	public void setDepartamentoId(Long departamentoId) {
		this.departamentoId = departamentoId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((departamentoId == null) ? 0 : departamentoId.hashCode());
		result = prime * result
				+ ((empresaId == null) ? 0 : empresaId.hashCode());
		result = prime * result
				+ ((proyectoId == null) ? 0 : proyectoId.hashCode());
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
		ProyectoDepartamentoPK other = (ProyectoDepartamentoPK) obj;
		if (departamentoId == null) {
			if (other.departamentoId != null)
				return false;
		} else if (!departamentoId.equals(other.departamentoId))
			return false;
		if (empresaId == null) {
			if (other.empresaId != null)
				return false;
		} else if (!empresaId.equals(other.empresaId))
			return false;
		if (proyectoId == null) {
			if (other.proyectoId != null)
				return false;
		} else if (!proyectoId.equals(other.proyectoId))
			return false;
		return true;
	}

}

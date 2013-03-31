package practica1.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_PROYECTOS_DEPARTAMENTOS")
@IdClass(ProyectoDepartamentoPK.class)
public class ProyectoDepartamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "C_PROYECTO_ID", insertable = false, updatable = false)
	private Long proyectoId;

	@Id
	@Column(name = "C_EMPRESA_ID", insertable = false, updatable = false)
	private Long empresaId;

	@Id
	@Column(name = "C_DEPARTAMENTO_ID", insertable = false, updatable = false)
	private Long departamentoId;

	@Column(name = "F_ENTRADA")
	@Temporal(TemporalType.DATE)
	private Date fechaEntrada;

	@JoinColumn(name = "C_PROYECTO_ID")
	private Proyecto proyecto;

	@ManyToOne
	@JoinColumns(value = {
			@JoinColumn(name = "C_EMPRESA_ID", referencedColumnName = "C_EMPRESA_ID"),
			@JoinColumn(name = "C_DEPARTAMENTO_ID", referencedColumnName = "C_DEPARTAMENTO_ID") })
	private Departamento departamento;

	@ManyToMany
	@JoinTable(name = "T_EMPLEADOS_TRABAJA", joinColumns = {
			@JoinColumn(name = "C_PROYECTO_ID", referencedColumnName = "C_PROYECTO_ID"),
			@JoinColumn(name = "C_EMPRESA_ID", referencedColumnName = "C_EMPRESA_ID"),
			@JoinColumn(name = "C_DEPARTAMENTO_ID", referencedColumnName = "C_DEPARTAMENTO_ID") }, inverseJoinColumns = { @JoinColumn(name = "C_EMPLEADO_ID", referencedColumnName = "C_EMPLEADO_ID") })
	private List<Empleado> empleados;

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

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
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
		ProyectoDepartamento other = (ProyectoDepartamento) obj;
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

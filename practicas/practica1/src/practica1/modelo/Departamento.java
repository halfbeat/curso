package practica1.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_DEPARTAMENTOS")
@IdClass(DepartamentoPK.class)
public class Departamento implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "C_EMPRESA_ID", insertable = false, updatable = false)
	private Long empresaId;

	@Id
	@Column(name = "C_DEPARTAMENTO_ID")
	private Long departamentoId;

	@Column(name = "D_NOMBRE", length = 50)
	private String nombre;

	@ManyToOne
	@JoinColumn(name = "C_RESPONSABLE_ID")
	private Empleado responsable;

	@ManyToOne
	@JoinColumn(name = "C_EMPRESA_ID")
	private Empresa empresa;

	@OneToMany(mappedBy = "departamento")
	private List<ProyectoDepartamento> proyectos = new ArrayList<ProyectoDepartamento>();

	@OneToMany(mappedBy = "departamentoTrabaja")
	private List<Empleado> empleados = new ArrayList<Empleado>();

	public Departamento() {
	}

	public Departamento(Empresa empresa, long id, String nombre) {
		this.empresa = empresa;
		this.departamentoId = id;
		this.nombre = nombre;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Empleado getResponsable() {
		return responsable;
	}

	public void setResponsable(Empleado responsable) {
		this.responsable = responsable;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<ProyectoDepartamento> getProyectos() {
		return proyectos;
	}

	public void setProyectos(List<ProyectoDepartamento> proyectos) {
		this.proyectos = proyectos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((departamentoId == null) ? 0 : departamentoId.hashCode());
		result = prime * result
				+ ((empresaId == null) ? 0 : empresaId.hashCode());
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
		Departamento other = (Departamento) obj;
		if (departamentoId == null) {
			if (other.departamentoId != null) {
				return false;
			}
		} else if (!departamentoId.equals(other.departamentoId)) {
			return false;
		}
		if (empresaId == null) {
			if (other.empresaId != null) {
				return false;
			}
		} else if (!empresaId.equals(other.empresaId)) {
			return false;
		}
		return true;
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}
}

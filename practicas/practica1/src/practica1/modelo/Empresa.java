package practica1.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_EMPRESAS")
@SequenceGenerator(name = "SEQ_EMPRESAS", initialValue = 1, allocationSize = 1)
public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "C_EMPRESA_ID")
	@GeneratedValue(generator = "SEQ_EMPRESAS")
	private Long empresaId;
	@Column(name = "D_NOMBRE", length = 100, unique = true)
	private String nombre;
	@Column(name = "F_ALTA")
	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
	@Column(name = "C_CIF_NIF", length = 9, unique = true)
	private String cifNif;
	@OneToMany(mappedBy = "empresa")
	private List<Empleado> empleados;
	@OneToMany(mappedBy = "empresa" /*,cascade = CascadeType.ALL, orphanRemoval = true*/)
	private List<Departamento> departamentos = new ArrayList<Departamento>();

	public Empresa() {
	}

	public Empresa(String nombre, String cifNif, Date fAlta) {
		this.nombre = nombre;
		this.fechaAlta = fAlta;
		this.cifNif = cifNif;
	}

	public Long getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(Long empresaId) {
		this.empresaId = empresaId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getCifNif() {
		return cifNif;
	}

	public void setCifNif(String cifNif) {
		this.cifNif = cifNif;
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Empresa other = (Empresa) obj;
		if (empresaId == null) {
			if (other.empresaId != null) {
				return false;
			}
		} else if (!empresaId.equals(other.empresaId)) {
			return false;
		}
		return true;
	}
}

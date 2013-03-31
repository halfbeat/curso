package practica1.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "T_EMPLEADO")
@SequenceGenerator(name = "SEQ_EMPLEADOS", initialValue = 1, allocationSize = 1)
public class Empleado implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "C_EMPLEADO_ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_EMPLEADOS")
	private Long empleadoId;
	@Column(name = "D_NOMBRE", length = 50, nullable = false)
	private String nombre;
	@Column(name = "D_APELLIDOS", length = 100, nullable = false)
	private String apellidos;
	@Column(name = "C_NIF", length = 9, nullable = false, unique = true)
	private String nif;
	@Column(name = "D_EMAIL", length = 100)
	private String email;
	@ManyToOne
	@JoinColumns(value = {
			@JoinColumn(name = "C_EMPRESA_ID", referencedColumnName = "C_EMPRESA_ID"),
			@JoinColumn(name = "C_DEPARTAMENTO_ID", referencedColumnName = "C_DEPARTAMENTO_ID") })
	private Departamento departamentoTrabaja;
	@ManyToOne
	@JoinColumn(name = "C_EMPRESA_ID", insertable = false, updatable = false)
	private Empresa empresa;
	@ManyToMany
	@JoinTable(name = "T_EMPLEADOS_TRABAJA", joinColumns = { @JoinColumn(name = "C_EMPLEADO_ID", referencedColumnName = "C_EMPLEADO_ID") }, inverseJoinColumns = { @JoinColumn(name = "C_PROYECTO_ID", referencedColumnName = "C_PROYECTO_ID") })
	private List<Proyecto> proyectosTrabaja;

	public Empleado() {
	}

	public Empleado(String cif, String nombre, String apellidos, String email) {
		this.nif = cif;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
	}

	public Long getEmpleadoId() {
		return empleadoId;
	}

	public void setEmpleadoId(Long empleadoId) {
		this.empleadoId = empleadoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Departamento getDepartamentoTrabaja() {
		return departamentoTrabaja;
	}

	public void setDepartamentoTrabaja(Departamento departamentoTrabaja) {
		this.departamentoTrabaja = departamentoTrabaja;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Proyecto> getProyectosTrabaja() {
		return proyectosTrabaja;
	}

	public void setProyectosTrabaja(List<Proyecto> proyectosTrabaja) {
		this.proyectosTrabaja = proyectosTrabaja;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((empleadoId == null) ? 0 : empleadoId.hashCode());
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
		Empleado other = (Empleado) obj;
		if (empleadoId == null) {
			if (other.empleadoId != null) {
				return false;
			}
		} else if (!empleadoId.equals(other.empleadoId)) {
			return false;
		}
		return true;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String cif) {
		this.nif = cif;
	}
}

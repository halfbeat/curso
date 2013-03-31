package practica1.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_PROYECTOS")
@SequenceGenerator(name = "SEQ_PROYECTOS", initialValue = 1, allocationSize = 1)
public class Proyecto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "C_PROYECTO_ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_PROYECTOS")
	private Long proyectoId;

	@Column(name = "D_NOMBRE", length = 100, nullable = false)
	private String nombre;

	@ManyToOne
	@JoinColumn(name = "C_CLIENTE_ID")
	private Cliente cliente;

	@JoinColumn(name = "C_ESTADO_ID", nullable = false)
	private Estado estado = Estado.EN_ESTUDIO;

	@ManyToMany(mappedBy = "proyectosTrabaja")
	private List<Empleado> empleadosTrabajan;

	public Proyecto() {
	}

	public Proyecto(String nombre) {
		this.nombre = nombre;
	}

	public Long getProyectoId() {
		return proyectoId;
	}

	public void setProyectoId(Long proyectoId) {
		this.proyectoId = proyectoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Proyecto other = (Proyecto) obj;
		if (proyectoId == null) {
			if (other.proyectoId != null)
				return false;
		} else if (!proyectoId.equals(other.proyectoId))
			return false;
		return true;
	}

}

package practica1.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_CLIENTES")
@SequenceGenerator(name = "SEQ_CLIENTES", initialValue = 1, allocationSize = 1)
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "C_CLIENTE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_CLIENTES")
    private Long clienteId;
    @Column(name = "C_CIF_NIF", length = 9, nullable = false)
    private String cifNif;
    @Column(name = "D_NOMBRE", length = 100, nullable = false)
    private String nombre;
    @OneToMany(mappedBy = "cliente")
    private List<Proyecto> proyectos;

    public Cliente() {
    }

    public Cliente(String nombre, String cifNif) {
        this.cifNif = cifNif;
        this.nombre = nombre;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getCifNif() {
        return cifNif;
    }

    public void setCifNif(String cifNif) {
        this.cifNif = cifNif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((clienteId == null) ? 0 : clienteId.hashCode());
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
        Cliente other = (Cliente) obj;
        if (clienteId == null) {
            if (other.clienteId != null) {
                return false;
            }
        } else if (!clienteId.equals(other.clienteId)) {
            return false;
        }
        return true;
    }
}

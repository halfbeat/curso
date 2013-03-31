package practica1.servicios;

import javax.persistence.EntityManager;

import practica1.modelo.Departamento;
import practica1.modelo.DepartamentoPK;
import practica1.utils.JpaDAO;

public class DepartamentoDAO extends JpaDAO<Departamento, DepartamentoPK> {

    public DepartamentoDAO(EntityManager em) {
        super(em);
    }
}

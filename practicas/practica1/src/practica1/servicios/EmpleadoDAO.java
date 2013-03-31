/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1.servicios;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import practica1.modelo.Empleado;
import practica1.modelo.Empleado_;
import practica1.utils.JpaDAO;

/**
 *
 * @author Angel
 */
public class EmpleadoDAO extends JpaDAO<Empleado, Long> {
    public EmpleadoDAO(EntityManager em) {
        super(em);
    }
    
    public Empleado findByNif(String nif) {
    	CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Empleado> cq = cb.createQuery(clazz);
		Root<Empleado> root = cq.from(Empleado.class);
		cq.where(cb.equal(root.get(Empleado_.nif), nif));
		try {
			return em.createQuery(cq).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
    }
}

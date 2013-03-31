package practica1.servicios;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import practica1.modelo.Empresa;
import practica1.modelo.Empresa_;
import practica1.utils.JpaDAO;

public class EmpresaDAO extends JpaDAO<Empresa, Long> {

	public EmpresaDAO() {
		super(null);
	}

	public EmpresaDAO(EntityManager em) {
		super(em);
	}

	public Empresa findByNombre(String nombre) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Empresa> cq = cb.createQuery(clazz);
		Root<Empresa> root = cq.from(Empresa.class);
		cq.where(cb.equal(root.get(Empresa_.nombre), nombre));
		try {
			return em.createQuery(cq).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Empresa findByCif(String nif) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Empresa> cq = cb.createQuery(clazz);
		Root<Empresa> root = cq.from(Empresa.class);
		cq.where(cb.equal(root.get(Empresa_.cifNif), nif));
		try {
			return em.createQuery(cq).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}

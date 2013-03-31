package practica1.utils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public abstract class JpaDAO<E, PK extends Serializable> implements
		IJpaDAO<E, PK> {
	protected EntityManager em;
	protected Class<E> clazz;

	@SuppressWarnings("unchecked")
	public JpaDAO(EntityManager em) {
		this.em = em;
		this.clazz = (Class<E>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void persist(E entidad) {
		em.persist(entidad);
	}

	public void removeById(PK id) {
		em.remove(findById(id));
	}

	public void remove(E e) {
		em.remove(e);
	}

	public E update(E entidad) {
		return em.merge(entidad);
	}

	public void refresh(E entidad) {
		em.refresh(entidad);
	}

	public E findById(PK clave) {
		return em.find(clazz, clave);
	}

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	public List<E> getAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<E> cq = cb.createQuery(clazz);
		return em.createQuery(cq).getResultList();
	}

}

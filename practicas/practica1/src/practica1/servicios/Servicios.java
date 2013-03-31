/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1.servicios;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import practica1.logica.Tuple2;

/**
 * 
 * @author Angel
 */
public class Servicios {
	private static EmpresaDAO empDAO = new EmpresaDAO(null);
	private static EmpleadoDAO emplDAO = new EmpleadoDAO(null);
	private static DepartamentoDAO depDAO = new DepartamentoDAO(null);
	private static ProyectoDAO proyDAO = new ProyectoDAO(null);
	private static ClienteDAO cliDAO = new ClienteDAO(null);
	private static EstadoDAO estDAO = new EstadoDAO(null);
	private static EntityManagerFactory emf;
	protected static ThreadLocal<Tuple2<EntityManager, Integer>> emThreadLocal = new ThreadLocal<Tuple2<EntityManager, Integer>>();

	public static synchronized void beginTransaction() {
		EntityManager em = null;
		if (emThreadLocal.get() == null) {
			em = emf.createEntityManager();
			emThreadLocal.set(new Tuple2<EntityManager, Integer>(em, 1));
		} else {
			em = emThreadLocal.get()._1;
			emThreadLocal.set(new Tuple2<EntityManager, Integer>(em,
					emThreadLocal.get()._2 + 1));
		}
		if (emThreadLocal.get()._2 == 1) {
			em.getTransaction().begin();
		} else {
		}
	}

	public static synchronized void commitTransaction() {
		EntityManager em = null;
		if (emThreadLocal.get() == null) {
			return;
		} else {
			em = emThreadLocal.get()._1;
			emThreadLocal.set(new Tuple2<EntityManager, Integer>(em,
					emThreadLocal.get()._2 - 1));
		}
		if (emThreadLocal.get()._2 == 0) {
			try {
				if (!em.getTransaction().getRollbackOnly())
					em.getTransaction().commit();
				em.close();
			} finally {
				emThreadLocal.set(null);
			}
		} else {
		}
	}

	public static synchronized void abortTransaction() {
		EntityManager em = null;
		if (emThreadLocal.get() == null) {
			return;
		} else {
			em = emThreadLocal.get()._1;
		}
		emThreadLocal.set(new Tuple2<EntityManager, Integer>(em, 0));
		try {
			if (!em.getTransaction().getRollbackOnly())
				em.getTransaction().rollback();
			em.close();
		} finally {
			emThreadLocal.set(null);
		}
	}

	public static EntityManager getEntityManager() {
		if (emThreadLocal.get() == null) {
			EntityManager em = emf.createEntityManager();
			emThreadLocal.set(new Tuple2<EntityManager, Integer>(em, 0));
			return em;
		} else {
			return emThreadLocal.get()._1;
		}
	}

	public static int getLevel() {
		Tuple2<EntityManager, Integer> em = emThreadLocal.get();
		return em == null ? 0 : em._2;
	}

	public static void setEntityManagerFactory(EntityManagerFactory emf) {
		Servicios.emf = emf;
	}

	public static EmpresaDAO EmpresaDAO() {
		empDAO.setEntityManager(getEntityManager());
		return empDAO;
	}

	public static EmpleadoDAO EmpleadoDAO() {
		emplDAO.setEntityManager(getEntityManager());
		return emplDAO;
	}

	public static DepartamentoDAO DepartamentoDAO() {
		depDAO.setEntityManager(getEntityManager());
		return depDAO;
	}

	public static ProyectoDAO ProyectoDAO() {
		proyDAO.setEntityManager(getEntityManager());
		return proyDAO;
	}

	public static ClienteDAO ClienteDAO() {
		cliDAO.setEntityManager(getEntityManager());
		return cliDAO;
	}

	public static EstadoDAO EstadoDAO() {
		estDAO.setEntityManager(getEntityManager());
		return estDAO;
	}
}

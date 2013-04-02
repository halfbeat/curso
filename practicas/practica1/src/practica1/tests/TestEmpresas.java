package practica1.tests;

import java.sql.SQLException;
import java.util.Date;

import javax.persistence.EntityManager;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import practica1.modelo.Empresa;

public class TestEmpresas extends TestBase {

	@Before
	public void clean() {
		EntityManager em = TestBase.emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.createQuery("delete from Empresa e").executeUpdate();
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	private Empresa crearEmpresa() {
		Empresa e = new Empresa();
		e.setCifNif("28347584J");
		e.setFechaAlta(new Date());
		e.setNombre("LACASITOS S.L.");
		return e;
	}

	private long contarEmpresas(EntityManager em) {
		return em.createQuery("select count(1) from Empresa e", Long.class)
				.getSingleResult();
	}

	@Test
	public void testAltaEmpresa() throws SQLException {
		EntityManager em = TestBase.emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Empresa e = crearEmpresa();
			em.persist(e);
			em.getTransaction().commit();

			e = em.find(Empresa.class, e.getEmpresaId());
			Assert.assertEquals(e.getCifNif(), "28347584J");
			Assert.assertEquals(e.getNombre(), "LACASITOS S.L.");
			Assert.assertTrue(e.getDepartamentos().isEmpty());
			Assert.assertTrue(e.getEmpleados().isEmpty());

		} finally {
			em.close();
		}
	}

	@Test
	public void testBajaEmpresa() {
		EntityManager em = TestBase.emf.createEntityManager();
		long count = contarEmpresas(em);
		try {
			em.getTransaction().begin();
			Empresa e = crearEmpresa();
			em.persist(e);
			em.getTransaction().commit();

			em.getTransaction().begin();
			em.remove(e);
			em.getTransaction().commit();

			Assert.assertNull(em.find(Empresa.class, e.getEmpresaId()));
			Assert.assertEquals(count, contarEmpresas(em));
		} finally {
			em.close();
		}
	}

	@Test
	public void testModificarEmpresa() {
		EntityManager em = TestBase.emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Empresa e = new Empresa();
			e.setCifNif("28347584J");
			e.setFechaAlta(new Date());
			e.setNombre("LACASITOS S.L.");
			em.persist(e);
			em.getTransaction().commit();
			em.getTransaction().begin();
			e.setNombre("LACASA");
			em.merge(e);
			em.getTransaction().commit();
			Assert.assertEquals(em.find(Empresa.class, e.getEmpresaId())
					.getNombre(), "LACASA");
		} finally {
			em.close();
		}
	}

}

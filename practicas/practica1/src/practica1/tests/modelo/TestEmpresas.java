package practica1.tests.modelo;

import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import practica1.modelo.Empresa;
import practica1.servicios.EmpresaDAO;
import practica1.servicios.Servicios;
import practica1.tests.TestBase;

public class TestEmpresas extends TestBase {

	@Before
	public void clean() {
		Empresa e = Servicios.EmpresaDAO().findByCif("28347584J");
		if (e != null) {
			Servicios.beginTransaction();
			Servicios.EmpresaDAO().remove(e);
			Servicios.commitTransaction();
		}

		e = Servicios.EmpresaDAO().findByCif("56434568H");
		if (e != null) {
			Servicios.beginTransaction();
			Servicios.EmpresaDAO().remove(e);
			Servicios.commitTransaction();
		}

	}

	@Test
	public void testAltaEmpresa() throws SQLException {
		Servicios.beginTransaction();
		EmpresaDAO eDao = Servicios.EmpresaDAO();
		
		Empresa e = new Empresa();
		e.setCifNif("28347584J");
		e.setFechaAlta(new Date());
		e.setNombre("LACASITOS S.L.");
		eDao.persist(e);
		Servicios.commitTransaction();

		Statement st = TestBase.jdbcConn.createStatement();
		try {
			ResultSet rs = st
					.executeQuery("SELECT COUNT(1) FROM T_EMPRESAS WHERE C_EMPRESA_ID="
							+ e.getEmpresaId());
			rs.next();
			assertTrue(rs.getInt(1) == 1);
		} finally {
			st.close();
		}
		Assert.assertEquals(e.getCifNif(), "28347584J");
		Assert.assertEquals(e.getNombre(), "LACASITOS S.L.");
		Assert.assertTrue(e.getDepartamentos().isEmpty());
		Assert.assertTrue(e.getEmpleados().isEmpty());
		Assert.assertEquals(e, Servicios.EmpresaDAO().findById(e.getEmpresaId()));

		st = TestBase.jdbcConn.createStatement();
		try {
			st.execute("UPDATE T_EMPRESAS SET C_CIF_NIF='56434568H' WHERE C_CIF_NIF='28347584J'");
		} finally {
			st.close();
		}
		Servicios.EmpresaDAO().refresh(e);
		Assert.assertEquals(e.getCifNif(), "56434568H");
	}

	@Test
	public void testBajaEmpresa() {
		EmpresaDAO eDao = Servicios.EmpresaDAO();
		int count = eDao.getAll().size();
		Servicios.beginTransaction();
		Empresa e = new Empresa();
		e.setCifNif("28347584J");
		e.setFechaAlta(new Date());
		e.setNombre("LACASITOS S.L.");
		eDao.persist(e);
		Servicios.commitTransaction();

		Servicios.beginTransaction();
		eDao.remove(e);
		Servicios.commitTransaction();
		Assert.assertEquals(count, eDao.getAll().size());
	}

	@Test
	public void testModificarEmpresa() {
		EmpresaDAO eDao = Servicios.EmpresaDAO();
		Servicios.beginTransaction();
		Empresa e = new Empresa();
		e.setCifNif("28347584J");
		e.setFechaAlta(new Date());
		e.setNombre("LACASITOS S.L.");
		eDao.persist(e);
		Servicios.commitTransaction();
		Servicios.beginTransaction();
		e.setNombre("LACASA");
		eDao.update(e);
		Servicios.commitTransaction();
		Assert.assertEquals(e.getNombre(), "LACASA");
	}

}

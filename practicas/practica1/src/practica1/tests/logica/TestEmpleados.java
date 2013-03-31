package practica1.tests.logica;

import javax.persistence.PersistenceException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import practica1.logica.DepartamentoNoExiste;
import practica1.logica.EmpleadoNoExiste;
import practica1.logica.EmpleadosService;
import practica1.logica.EmpresasService;
import practica1.modelo.Empleado;
import practica1.modelo.Empresa;
import practica1.servicios.Servicios;
import practica1.tests.TestBase;

//@RunWith(WithTransaction.class)
public class TestEmpleados extends TestBase {
	@Before
	public void clean() {
		Empleado e = Servicios.EmpleadoDAO().findByNif("11111111Q");
		if (e != null) {
			Servicios.beginTransaction();
			Servicios.EmpleadoDAO().remove(e);
			Servicios.commitTransaction();
		}
	}

	@Test
	public void testAltaEmpleado() {
		Empleado e = EmpleadosService.altaEmpleado("11111111Q", "ALBERTO",
				"PEREZ JIMENEZ", "perezalj@jjj.com");
		Assert.assertEquals(e, EmpleadosService.buscarPorNif("11111111Q"));
	}

	@Test(expected = PersistenceException.class)
	public void testAltaEmpleadoDuplicada() {
		EmpleadosService.altaEmpleado("11111111Q", "ALBERTO", "PEREZ JIMENEZ",
				"perezalj@jjj.com");
		EmpleadosService.altaEmpleado("11111111Q", "ALBERTO", "PEREZ JIMENEZ",
				"perezalj@jjj.com");
	}

	@Test
	public void bajaEmpleado() throws EmpleadoNoExiste {
		Empleado e = EmpleadosService.altaEmpleado("11111111Q", "ALBERTO",
				"PEREZ JIMENEZ", "perezalj@jjj.com");
		EmpleadosService.bajaEmpleado(e);
	}

	@Test
	public void testModificarEmpleado() throws EmpleadoNoExiste {
		Empleado e = EmpleadosService.altaEmpleado("11111111Q", "ALBERTO",
				"PEREZ JIMENEZ", "perezalj@jjj.com");
		e.setNif("22222222Q");
		e.setNombre("FELICIANO");
		EmpleadosService.modificarEmpleado(e);
	}

	@Test
	public void testAsociarEmpleado() throws DepartamentoNoExiste,
			EmpleadoNoExiste {
		Empleado emp = EmpleadosService.altaEmpleado("11111111Q", "ALBERTO",
				"PEREZ JIMENEZ", "perezalj@jjj.com");
		Empresa empresa = EmpresasService.buscarPorNombre("MECANSA S.A.");
		Assert.assertNotNull(empresa);
		Assert.assertTrue(empresa.getDepartamentos().size() > 0);
		EmpleadosService
				.asociarEmpleado(emp, empresa.getDepartamentos().get(0));
	}
}

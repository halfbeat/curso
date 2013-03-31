package practica1.tests.logica;

import java.util.Date;

import javax.persistence.PersistenceException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import practica1.logica.DepartamentoNoExiste;
import practica1.logica.DepartamentoYaExiste;
import practica1.logica.EmpleadoNoExiste;
import practica1.logica.EmpleadosService;
import practica1.logica.EmpresaNoExiste;
import practica1.logica.EmpresasService;
import practica1.modelo.Departamento;
import practica1.modelo.DepartamentoPK;
import practica1.modelo.Empresa;
import practica1.servicios.Servicios;
import practica1.tests.TestBase;

//@RunWith(WithTransaction.class)
public class TestEmpresas extends TestBase {

	@Before
	public void clean() {
		Empresa e = Servicios.EmpresaDAO().findByNombre("LAPEPITA");
		if (e != null) {
			Servicios.beginTransaction();
			Servicios.EmpresaDAO().remove(e);
			for (Departamento d : e.getDepartamentos()) {
				Servicios.DepartamentoDAO().remove(d);
			}
			Servicios.commitTransaction();
		}
	}

	@Test
	public void testAltaEmpresa() {
		EmpresasService.altaEmpresa("LAPEPITA", "11111111Q", new Date());
	}

	@Test
	public void bajaEmpresa() throws EmpresaNoExiste {
		Empresa e = EmpresasService.altaEmpresa("LAPEPITA", "11111111Q",
				new Date());
		EmpresasService.bajaEmpresa(e);
	}

	@Test(expected = PersistenceException.class)
	public void testAltaEmpresaDuplicada() {
		EmpresasService.altaEmpresa("LAPEPITA", "11111111Q", new Date());
		EmpresasService.altaEmpresa("LAPEPITA", "11111111Q", new Date());
	}

	@Test
	public void testModificarEmpresa() throws EmpresaNoExiste {
		Empresa e = EmpresasService.altaEmpresa("LAPEPITA", "11111111Q",
				new Date());
		e.setCifNif("22222222Q");
		EmpresasService.modificarEmpresa(e);
	}

	@Test
	public void testAltaDepartamento() throws EmpresaNoExiste,
			DepartamentoYaExiste {
		Empresa e = EmpresasService.altaEmpresa("LAPEPITA", "11111111Q",
				new Date());
		EmpresasService.altaDepartamento(e, "COSTES");
	}

	@Test(expected = DepartamentoYaExiste.class)
	public void testAltaDepartamentoDuplicado() throws EmpresaNoExiste,
			DepartamentoYaExiste {
		Empresa e = EmpresasService.altaEmpresa("LAPEPITA", "11111111Q",
				new Date());
		EmpresasService.altaDepartamento(e, "COSTES");
		EmpresasService.altaDepartamento(e, "COSTES");
	}

	@Test
	public void testBajaDepartamento() throws EmpresaNoExiste,
			DepartamentoYaExiste, DepartamentoNoExiste {
		Empresa e = EmpresasService.altaEmpresa("LAPEPITA", "11111111Q",
				new Date());
		Departamento d = EmpresasService.altaDepartamento(e, "COSTES");
		EmpresasService.bajaDepartamento(e, d.getDepartamentoId());
	}

	@Test(expected = DepartamentoNoExiste.class)
	public void testBajaDepartamentoNoExiste() throws EmpresaNoExiste,
			DepartamentoYaExiste, DepartamentoNoExiste {
		Empresa e = EmpresasService.altaEmpresa("LAPEPITA", "11111111Q",
				new Date());
		EmpresasService.bajaDepartamento(e, 3342);
	}

	@Test
	public void testModificarDepartamento() throws EmpresaNoExiste,
			DepartamentoYaExiste, DepartamentoNoExiste {
		Empresa e = EmpresasService.altaEmpresa("LAPEPITA", "11111111Q",
				new Date());
		Departamento d = EmpresasService.altaDepartamento(e, "COSTES");
		d.setNombre("JUEGOS");
		EmpresasService.modificarDepartamento(d);
	}

	@Test
	public void testModificarResponsableDepartamento() throws EmpresaNoExiste,
			DepartamentoYaExiste, DepartamentoNoExiste {
		Empresa e = EmpresasService.altaEmpresa("LAPEPITA", "11111111Q",
				new Date());
		Departamento d = EmpresasService.altaDepartamento(e, "COSTES");
		EmpresasService.modificarResponsableDepartamento(d, null);
		d = EmpresasService.modificarResponsableDepartamento(d,
				EmpleadosService.buscarPorNif("00000000X"));
		Assert.assertNotNull(d.getResponsable());
	}

	@Test
	public void testListadoEmpleadosDepartamento() throws DepartamentoNoExiste,
			EmpresaNoExiste, DepartamentoYaExiste, EmpleadoNoExiste {
		Empresa e = null;
		Departamento d = null;
		e = EmpresasService.altaEmpresa("LAPEPITA", "11111111Q", new Date());
		d = EmpresasService.altaDepartamento(e, "COSTES");
		EmpleadosService.asociarEmpleado(
				EmpleadosService.buscarPorNif("00000000X"), d);
		EmpleadosService.asociarEmpleado(
				EmpleadosService.buscarPorNif("00000001X"), d);
		EmpleadosService.asociarEmpleado(
				EmpleadosService.buscarPorNif("00000002X"), d);
		EmpleadosService.asociarEmpleado(
				EmpleadosService.buscarPorNif("00000003X"), d);
		EmpleadosService.asociarEmpleado(
				EmpleadosService.buscarPorNif("00000004X"), d);
		Assert.assertEquals(5, d.getEmpleados().size());

		d = Servicios.DepartamentoDAO().findById(
				new DepartamentoPK(e.getEmpresaId(), d.getDepartamentoId()));
		Assert.assertEquals(5, d.getEmpleados().size());

	}
}

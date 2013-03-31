package practica1.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import practica1.modelo.Cliente;
import practica1.modelo.Departamento;
import practica1.modelo.Empleado;
import practica1.modelo.Empresa;
import practica1.modelo.Estado;
import practica1.modelo.Proyecto;
import practica1.servicios.Servicios;

public class TestBase {

	private static EntityManagerFactory emf = null;
	protected static Connection jdbcConn;

	protected boolean transaccional = true;

	@BeforeClass
	public static void inicioPruebas() throws SQLException {
		emf = Persistence.createEntityManagerFactory("practica1");
		// jdbcConn =
		// DriverManager.getConnection("jdbc:derby:memory:test-jpa;create=true");
		jdbcConn = DriverManager
				.getConnection("jdbc:derby://localhost:1527/practica1");
		Servicios.setEntityManagerFactory(emf);
		// populate();
	}

	@AfterClass
	public static void finPruebas() {
		if (emf != null) {
			emf.close();
			Servicios.setEntityManagerFactory(null);
		}
	}

	protected static void borrarTodo() {
		final EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		try {

			for (Empresa e : Servicios.EmpresaDAO().getAll()) {
				for (Departamento d : e.getDepartamentos()) {
					d.setResponsable(null);
					Servicios.DepartamentoDAO().update(d);
				}
			}

			for (Empleado e : Servicios.EmpleadoDAO().getAll()) {
				e.setProyectosTrabaja(Collections.<Proyecto> emptyList());
				e.setDepartamentoTrabaja(null);
				Servicios.EmpleadoDAO().update(e);
				Servicios.EmpleadoDAO().removeById(e.getEmpleadoId());
			}

			for (Empresa e : Servicios.EmpresaDAO().getAll()) {
				Servicios.EmpresaDAO().removeById(e.getEmpresaId());
			}

			for (Proyecto p : Servicios.ProyectoDAO().getAll()) {
				Servicios.ProyectoDAO().removeById(p.getProyectoId());
			}

			for (Cliente c : Servicios.ClienteDAO().getAll()) {
				Servicios.ClienteDAO().removeById(c.getClienteId());
			}

			for (Estado e : Servicios.EstadoDAO().getAll()) {
				Servicios.EstadoDAO().removeById(e.getEstadoId());
			}
		} finally {
			entityManager.getTransaction().commit();
		}

	}

	protected static void populate() {
		Servicios.beginTransaction();
		try {

			Empleado[] empleados = new Empleado[] {
					new Empleado("00000000X", "PERICO", "DE LOS PALOTES",
							"perico@gmail.com"),
					new Empleado("00000001X", "LUIS", "MUNGEZ",
							"lminguez@gmail.com"),
					new Empleado("00000002X", "ROSA", "APEROS VALE",
							"rosaap@gmail.com"),
					new Empleado("00000003X", "MARIA", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000004X", "JESUS", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000005X", "RAQUEL", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000006X", "JULIO", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000007X", "ALFREDO", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000008X", "MIGUEL", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000009X", "SARA", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000010X", "LORETO", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000011X", "LAURA", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000012X", "ANGEL", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000013X", "CRISTINA", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000014X", "SOLEDAD", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000015X", "JORGE", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000016X", "JAVIER", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000017X", "ALBERTO", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000018X", "RICARDO", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000019X", "MARGARITA", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000020X", "FULGENCIO", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000021X", "DAVID", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000022X", "LAURA", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000023X", "VERONICA", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000024X", "RAFAEL", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000025X", "PEDRO", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000026X", "SANDRA", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000027X", "MONICA", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000028X", "Mª LUISA", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000029X", "ASUNCION", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000030X", "RODRIGO", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000031X", "JUAN", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000032X", "CARLOS", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000033X", "ROSA", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000034X", "NURIA", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000035X", "MIGUEL", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000036X", "LUIS", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000037X", "NEREA", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000038X", "NOELIA", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000039X", "JULIAN", "RUN RUN",
							"runrunm@gmail.com"),
					new Empleado("00000040X", "ANDRES", "POCO CURRO",
							"andpoc@gmail.com") };

			Empresa[] empresas = new Empresa[] {
					new Empresa("POCOSA S.A.", "93943959H", new Date()),
					new Empresa("MECANSA S.A.", "28384805M", new Date()),
					new Empresa("MIEDITO S.L.", "04353439Y", new Date()) };

			Cliente[] clientes = new Cliente[] {
					new Cliente("FROJOSA", "33443435O"),
					new Cliente("PARMISO", "34937593D"),
					new Cliente("JOROSCA", "19385028H"),
					new Cliente("PREMAR", "09385394U"),
					new Cliente("GRIMOSO", "83950230N") };

			Proyecto[] proyectos = new Proyecto[] { new Proyecto("PROYECTO 1"),
					new Proyecto("PROYECTO 2"), new Proyecto("PROYECTO 3"),
					new Proyecto("PROYECTO 4"), new Proyecto("PROYECTO 5") };

			for (Empresa e : empresas) {
				Departamento d1 = new Departamento(e, 1, "RECURSOS HUMANOS");
				Departamento d2 = new Departamento(e, 2, "INGENIERIA");
				Departamento d3 = new Departamento(e, 3, "DIRECCION");
				Servicios.DepartamentoDAO().persist(d1);
				Servicios.DepartamentoDAO().persist(d2);
				Servicios.DepartamentoDAO().persist(d3);
				e.getDepartamentos().add(d1);
				e.getDepartamentos().add(d2);
				e.getDepartamentos().add(d3);
			}

			// int idxEmp = 0;
			// int idxEmpl = 0;
			// int idxDep = 0;
			// for (Empleado e : empleados) {
			// Empresa emp = empresas[idxEmp];
			// Departamento dep = emp.getDepartamentos().get(idxDep);
			// e.setDepartamentoTrabaja(dep);
			// idxDep++;
			// if (idxDep >= emp.getDepartamentos().size()) {
			// idxDep = 0;
			// idxEmp++;
			// if (idxEmp >= empresas.length) {
			// idxEmp = 0;
			// }
			// }
			// }
			//
			// idxEmp = 0;
			// for (Empresa e : empresas) {
			// for (Departamento d : e.getDepartamentos()) {
			// d.setResponsable(empleados[idxEmp]);
			// idxEmpl += 5;
			// if (idxEmpl >= empleados.length) {
			// idxEmpl = 0;
			// }
			// }
			// }
			//
			// int idxPr = 0;
			// for (Cliente c : clientes) {
			// proyectos[idxPr].setCliente(c);
			// idxPr++;
			// if (idxPr >= proyectos.length) {
			// idxPr = 0;
			// }
			// }

			for (Estado e : Arrays.asList(Estado.EN_ESTUDIO, Estado.CANCELADO,
					Estado.EN_DESARROLLO, Estado.ENTREGADO, Estado.FINALIZADO)) {
				Servicios.EstadoDAO().persist(e);
			}

			for (Empleado e : empleados) {
				Servicios.EmpleadoDAO().persist(e);
			}

			for (Empresa e : empresas) {
				Servicios.EmpresaDAO().persist(e);
			}

			for (Cliente c : clientes) {
				Servicios.ClienteDAO().persist(c);
			}

			for (Proyecto p : proyectos) {
				Servicios.ProyectoDAO().persist(p);
			}

			Proyecto p1 = new Proyecto();
			p1.setNombre("PROYECTO1");
			p1.setCliente(clientes[0]);
			Servicios.ProyectoDAO().persist(p1);
		} finally {
			Servicios.commitTransaction();
		}
	}
}

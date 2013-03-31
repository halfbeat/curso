package practica1.logica;

import practica1.modelo.Departamento;
import practica1.modelo.Empleado;
import practica1.servicios.Servicios;

public class EmpleadosService {
	public static Empleado altaEmpleado(String nif, String nombre,
			String apellidos, String email) {
		Servicios.beginTransaction();
		try {
			Empleado e = new Empleado();
			e.setNif(nif);
			e.setNombre(nombre);
			e.setApellidos(apellidos);
			e.setEmail(email);
			Servicios.EmpleadoDAO().persist(e);
			return e;
		} finally {
			Servicios.commitTransaction();
		}
	}

	public static Empleado buscarPorNif(String nif) {
		return Servicios.EmpleadoDAO().findByNif(nif);
	}

	public static void bajaEmpleado(Empleado e) throws EmpleadoNoExiste {
		Servicios.beginTransaction();
		try {
			if (Servicios.EmpleadoDAO().findById(e.getEmpleadoId()) == null) {
				Servicios.abortTransaction();
				throw new EmpleadoNoExiste("La Empleado no exite");
			}
			Servicios.EmpleadoDAO().remove(e);
		} finally {
			Servicios.commitTransaction();
		}

	}

	public static Empleado modificarEmpleado(Empleado e)
			throws EmpleadoNoExiste {
		Servicios.beginTransaction();
		try {
			Empleado empleado = new Empleado();
			empleado.setEmpleadoId(e.getEmpleadoId());
			empleado.setNif(e.getNif());
			empleado.setNombre(e.getNombre());
			empleado.setApellidos(e.getApellidos());
			empleado.setEmail(e.getEmail());
			return Servicios.EmpleadoDAO().update(empleado);
		} finally {
			Servicios.commitTransaction();
		}
	}

	public static void asociarEmpleado(Empleado emp, Departamento d)
			throws DepartamentoNoExiste, EmpleadoNoExiste {
		Servicios.beginTransaction();
		try {
			d.getEmpleados().add(emp);
			Servicios.DepartamentoDAO().update(d);
			emp.setDepartamentoTrabaja(d);
			Servicios.EmpleadoDAO().update(emp);
		} finally {
			Servicios.commitTransaction();
		}

	}
}

package practica1.logica;

import java.util.Date;

import practica1.modelo.Departamento;
import practica1.modelo.Empleado;
import practica1.modelo.Empresa;
import practica1.servicios.Servicios;

public class EmpresasService {
	public static Empresa altaEmpresa(String nombre, String cifNif,
			Date fechaAlta) {
		Servicios.beginTransaction();
		try {
			Empresa e = new Empresa();
			e.setCifNif(cifNif);
			e.setNombre(nombre);
			e.setFechaAlta(fechaAlta);
			Servicios.EmpresaDAO().persist(e);
			return e;
		} finally {
			Servicios.commitTransaction();
		}
	}

	public static Empresa buscarPorNombre(String nombre) {
		return Servicios.EmpresaDAO().findByNombre(nombre);
	}

	public static void bajaEmpresa(Empresa e) {
		Servicios.beginTransaction();
		try {
			Servicios.EmpresaDAO().remove(e);
		} finally {
			Servicios.commitTransaction();
		}

	}

	public static Empresa modificarEmpresa(Empresa e) throws EmpresaNoExiste {
		Servicios.beginTransaction();
		try {
			Empresa empresa = Servicios.EmpresaDAO().update(e);
			empresa.setEmpresaId(e.getEmpresaId());
			empresa.setCifNif(e.getCifNif());
			empresa.setNombre(e.getNombre());
			empresa.setFechaAlta(e.getFechaAlta());
			return empresa;
		} finally {
			Servicios.commitTransaction();
		}
	}

	public static Departamento altaDepartamento(Empresa e, String nombre)
			throws EmpresaNoExiste, DepartamentoYaExiste {
		Servicios.beginTransaction();
		try {

			long max = 0;
			for (Departamento dd : e.getDepartamentos()) {
				if (dd.getNombre().equals(nombre)) {
					throw new DepartamentoYaExiste(
							"Un departamento con nombre " + nombre
									+ " existe en la empresa");
				}
				if (dd.getDepartamentoId() > max) {
					max = dd.getDepartamentoId();
				}
			}
			Departamento d = new Departamento(e, max + 1, nombre);
			Servicios.DepartamentoDAO().persist(d);
			e.getDepartamentos().add(d);
			Servicios.EmpresaDAO().update(e);
			return d;
		} finally {
			Servicios.commitTransaction();
		}
	}

	public static Empresa bajaDepartamento(Empresa e, long departamentoId)
			throws EmpresaNoExiste, DepartamentoNoExiste {
		Servicios.beginTransaction();
		try {
			Departamento encontrado = null;
			for (Departamento dd : e.getDepartamentos()) {
				if (dd.getDepartamentoId().equals(departamentoId)) {
					encontrado = dd;
					break;
				}
			}
			if (encontrado == null) {
				throw new DepartamentoNoExiste("El departamento "
						+ departamentoId + " no pertenece a la empresa " + e);
			}
			e.getDepartamentos().remove(encontrado);
			Servicios.DepartamentoDAO().remove(encontrado);
			return Servicios.EmpresaDAO().update(e);
		} finally {
			Servicios.commitTransaction();
		}

	}

	public static Departamento modificarDepartamento(Departamento d)
			throws DepartamentoNoExiste, EmpresaNoExiste {
		Servicios.beginTransaction();
		try {
			if (d.getEmpresa() == null) {
				throw new EmpresaNoExiste("La empresa no exite");
			}
			Departamento encontrado = null;
			for (Departamento dd : d.getEmpresa().getDepartamentos()) {
				if (dd.equals(d)) {
					encontrado = dd;
					break;
				}
			}
			if (encontrado == null) {
				throw new DepartamentoNoExiste("El departamento " + d
						+ " no pertenece a la empresa " + d.getEmpresa());
			}
			encontrado.setNombre(d.getNombre());
			return Servicios.DepartamentoDAO().update(encontrado);
		} finally {
			Servicios.commitTransaction();
		}
	}

	public static Departamento modificarResponsableDepartamento(Departamento d,
			Empleado responsable) throws DepartamentoNoExiste, EmpresaNoExiste {
		Servicios.beginTransaction();
		try {
			if (d.getEmpresa() == null) {
				throw new EmpresaNoExiste("La empresa no exite");
			}
			Departamento encontrado = null;
			for (Departamento dd : d.getEmpresa().getDepartamentos()) {
				if (dd.equals(d)) {
					encontrado = dd;
					break;
				}
			}
			if (encontrado == null) {
				throw new DepartamentoNoExiste("El departamento " + d
						+ " no pertenece a la empresa " + d.getEmpresa());
			}
			encontrado.setResponsable(responsable);
			return Servicios.DepartamentoDAO().update(encontrado);
		} finally {
			Servicios.commitTransaction();
		}
	}
}

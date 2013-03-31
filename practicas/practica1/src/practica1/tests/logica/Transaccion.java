package practica1.tests.logica;

import practica1.servicios.Servicios;

public class Transaccion {
	public static void transaccion(Runnable r) {
		Servicios.beginTransaction();
		try {
			r.run();
		} finally {
			Servicios.commitTransaction();
		}
	}
}

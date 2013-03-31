package practica1.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(value = { practica1.tests.modelo.TestEmpresas.class,
		practica1.tests.logica.TestEmpresas.class,
		practica1.tests.logica.TestEmpleados.class })
public class TestSuite {
}

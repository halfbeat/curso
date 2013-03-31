package practica1.tests.logica;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import practica1.servicios.Servicios;

public class WithTransaction extends BlockJUnit4ClassRunner {

	public WithTransaction(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	protected Statement methodBlock(final FrameworkMethod method) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				Servicios.beginTransaction();
				try {
					WithTransaction.super.methodBlock(method).evaluate();
				} finally {
					Servicios.commitTransaction();
				}
			}
		};
	}

}

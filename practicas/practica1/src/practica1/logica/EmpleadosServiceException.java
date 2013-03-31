package practica1.logica;

public class EmpleadosServiceException extends Exception {
	private static final long serialVersionUID = 1L;

	public EmpleadosServiceException() {
	}

	public EmpleadosServiceException(String message) {
		super(message);
	}

	public EmpleadosServiceException(Throwable cause) {
		super(cause);
	}

	public EmpleadosServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}

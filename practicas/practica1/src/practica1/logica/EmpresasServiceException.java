package practica1.logica;

public class EmpresasServiceException extends Exception {
	private static final long serialVersionUID = 1L;

	public EmpresasServiceException() {
	}

	public EmpresasServiceException(String message) {
		super(message);
	}

	public EmpresasServiceException(Throwable cause) {
		super(cause);
	}

	public EmpresasServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}

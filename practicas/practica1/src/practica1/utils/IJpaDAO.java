package practica1.utils;

public interface IJpaDAO<E, PK> {
	void persist(E entidad);

	void removeById(PK id);
	
	void remove(E id);

	E update(E entidad);

	void refresh(E entidad);

	E findById(PK clave);
}

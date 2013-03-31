package practica1.modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-03-29T18:46:26.884+0100")
@StaticMetamodel(Empresa.class)
public class Empresa_ {
	public static volatile SingularAttribute<Empresa, Long> empresaId;
	public static volatile SingularAttribute<Empresa, String> nombre;
	public static volatile SingularAttribute<Empresa, Date> fechaAlta;
	public static volatile SingularAttribute<Empresa, String> cifNif;
	public static volatile ListAttribute<Empresa, Empleado> empleados;
	public static volatile ListAttribute<Empresa, Departamento> departamentos;
}

package practica1.modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-03-30T11:42:50.899+0100")
@StaticMetamodel(Empleado.class)
public class Empleado_ {
	public static volatile SingularAttribute<Empleado, Long> empleadoId;
	public static volatile SingularAttribute<Empleado, String> nombre;
	public static volatile SingularAttribute<Empleado, String> apellidos;
	public static volatile SingularAttribute<Empleado, String> nif;
	public static volatile SingularAttribute<Empleado, String> email;
	public static volatile SingularAttribute<Empleado, Departamento> departamentoTrabaja;
	public static volatile SingularAttribute<Empleado, Empresa> empresa;
	public static volatile ListAttribute<Empleado, Proyecto> proyectosTrabaja;
}

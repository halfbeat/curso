package practica1.modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-03-22T23:50:54.127+0100")
@StaticMetamodel(Proyecto.class)
public class Proyecto_ {
	public static volatile SingularAttribute<Proyecto, Long> proyectoId;
	public static volatile SingularAttribute<Proyecto, String> nombre;
	public static volatile SingularAttribute<Proyecto, Cliente> cliente;
	public static volatile SingularAttribute<Proyecto, Estado> estado;
	public static volatile ListAttribute<Proyecto, Empleado> empleadosTrabajan;
}

package practica1.modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-03-22T23:56:48.150+0100")
@StaticMetamodel(ProyectoDepartamento.class)
public class ProyectoDepartamento_ {
	public static volatile SingularAttribute<ProyectoDepartamento, Long> proyectoId;
	public static volatile SingularAttribute<ProyectoDepartamento, Long> empresaId;
	public static volatile SingularAttribute<ProyectoDepartamento, Long> departamentoId;
	public static volatile SingularAttribute<ProyectoDepartamento, Date> fechaEntrada;
	public static volatile SingularAttribute<ProyectoDepartamento, Proyecto> proyecto;
	public static volatile SingularAttribute<ProyectoDepartamento, Departamento> departamento;
	public static volatile ListAttribute<ProyectoDepartamento, Empleado> empleados;
}

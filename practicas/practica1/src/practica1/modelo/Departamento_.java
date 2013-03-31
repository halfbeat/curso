package practica1.modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-03-29T14:39:37.561+0100")
@StaticMetamodel(Departamento.class)
public class Departamento_ {
	public static volatile SingularAttribute<Departamento, Long> empresaId;
	public static volatile SingularAttribute<Departamento, Long> departamentoId;
	public static volatile SingularAttribute<Departamento, String> nombre;
	public static volatile SingularAttribute<Departamento, Empleado> responsable;
	public static volatile SingularAttribute<Departamento, Empresa> empresa;
	public static volatile ListAttribute<Departamento, ProyectoDepartamento> proyectos;
	public static volatile ListAttribute<Departamento, Empleado> empleados;
}

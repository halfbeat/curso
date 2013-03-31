package practica1.modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-03-16T11:21:02.710+0100")
@StaticMetamodel(Cliente.class)
public class Cliente_ {
	public static volatile SingularAttribute<Cliente, Long> clienteId;
	public static volatile SingularAttribute<Cliente, String> cifNif;
	public static volatile SingularAttribute<Cliente, String> nombre;
	public static volatile ListAttribute<Cliente, Proyecto> proyectos;
}

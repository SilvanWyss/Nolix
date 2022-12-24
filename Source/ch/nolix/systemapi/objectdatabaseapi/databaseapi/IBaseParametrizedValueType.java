//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//interface
public interface IBaseParametrizedValueType<
	IMPL,
	V
>
extends IParametrizedPropertyType<IMPL> {
	
	//method declaration
	Class<V> getValueType();
}

//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//interface
public interface IBaseParametrizedValueType<

	V
>
extends IParameterizedPropertyType {
	
	//method declaration
	Class<V> getValueType();
}

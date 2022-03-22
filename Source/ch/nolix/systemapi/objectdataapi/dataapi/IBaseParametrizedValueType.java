//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//interface
public interface IBaseParametrizedValueType<
	IMPL,
	V
> extends IParametrizedPropertyType<IMPL> {
	
	//method declaration
	Class<V> getValueType();
}

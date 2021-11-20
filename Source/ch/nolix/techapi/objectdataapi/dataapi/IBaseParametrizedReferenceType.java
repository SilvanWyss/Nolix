//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//interface
public interface IBaseParametrizedReferenceType<
	IMPL,
	E extends IEntity<IMPL>
> extends IParametrizedPropertyType<IMPL> {
	
	//method declaration
	Class<E> getEntityType();
}

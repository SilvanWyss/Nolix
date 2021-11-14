//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//interface
public interface IBaseParametrizedReferenceType<E extends IEntity<E, ?>> extends IParametrizedPropertyType {
	
	//method declaration
	Class<E> getEntityType();
}

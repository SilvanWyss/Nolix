//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

//interface
public interface IParametrizedPropertyType<IMPL> {
	
	//method declaration
	boolean canReferenceEntityOfType(final Class<IEntity<IMPL>> type);
	
	//method declaration
	PropertyType getPropertyType();
}

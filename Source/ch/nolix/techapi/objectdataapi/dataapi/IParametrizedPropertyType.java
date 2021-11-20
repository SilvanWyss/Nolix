//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.techapi.databaseapi.propertytypeapi.PropertyType;

//interface
public interface IParametrizedPropertyType<IMPL> {
	
	//method declaration
	boolean canReferenceEntityOfType(final Class<IEntity<IMPL>> type);
	
	//method declaration
	PropertyType getPropertyType();
}

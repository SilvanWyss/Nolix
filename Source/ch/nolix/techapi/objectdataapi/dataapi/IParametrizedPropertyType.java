//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.techapi.databaseapi.propertytypeapi.PropertyType;

//interface
public interface IParametrizedPropertyType {
	
	//method declaration
	boolean canReferenceEntityOfType(final Class<IEntity<?, ?>> type);
	
	//method declaration
	PropertyType getPropertyType();
}

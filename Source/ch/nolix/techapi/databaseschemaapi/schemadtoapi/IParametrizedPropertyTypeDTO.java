//package declaration
package ch.nolix.techapi.databaseschemaapi.schemadtoapi;

//own imports
import ch.nolix.techapi.databaseschemaapi.propertytypeapi.PropertyType;

//interface
public interface IParametrizedPropertyTypeDTO {
	
	//method declaration
	String getDataTypeFullClassName();
	
	//method declaration
	PropertyType getPropertyType();
}

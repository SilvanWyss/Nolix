//package declaration
package ch.nolix.techapi.databaseschemaapi.schemadtoapi;

//own imports
import ch.nolix.techapi.databasecommonapi.propertytypeapi.PropertyType;

//interface
public interface IBaseParametrizedPropertyTypeDTO {
	
	//method declaration
	String getDataTypeFullClassName();
	
	//method declaration
	PropertyType getPropertyType();
}

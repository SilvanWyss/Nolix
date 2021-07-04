//package declaration
package ch.nolix.techapi.databaseschemaapi.schemadtoapi;

import ch.nolix.techapi.databasecommonapi.propertytypeapi.PropertyType;

//interface
public interface IParametrizedPropertyTypeDTO {
	
	//method declaration
	String getDataTypeFullClassName();
	
	//method declaration
	PropertyType getPropertyType();
}

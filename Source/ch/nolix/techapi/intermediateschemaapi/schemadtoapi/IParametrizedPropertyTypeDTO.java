//package declaration
package ch.nolix.techapi.intermediateschemaapi.schemadtoapi;

import ch.nolix.techapi.databaseapi.propertytypeapi.PropertyType;

//interface
public interface IParametrizedPropertyTypeDTO {
	
	//method declaration
	String getDataTypeFullClassName();
	
	//method declaration
	PropertyType getPropertyType();
}

//package declaration
package ch.nolix.systemapi.rawschemaapi.schemadtoapi;

import ch.nolix.systemapi.entitypropertyapi.datatypeapi.DataType;
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;

//interface
public interface IParameterizedPropertyTypeDto {

  //method declaration
  DataType getDataType();

  //method declaration
  PropertyType getPropertyType();
}

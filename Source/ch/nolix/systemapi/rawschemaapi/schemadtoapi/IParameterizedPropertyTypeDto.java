//package declaration
package ch.nolix.systemapi.rawschemaapi.schemadtoapi;

//own imports
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

//interface
public interface IParameterizedPropertyTypeDto {

  // method declaration
  DataType getDataType();

  // method declaration
  PropertyType getPropertyType();
}

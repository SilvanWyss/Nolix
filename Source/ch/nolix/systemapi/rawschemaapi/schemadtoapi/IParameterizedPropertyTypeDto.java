//package declaration
package ch.nolix.systemapi.rawschemaapi.schemadtoapi;

import ch.nolix.systemapi.fieldapi.datatypeapi.DataType;
import ch.nolix.systemapi.fieldapi.mainapi.FieldType;

//interface
public interface IParameterizedPropertyTypeDto {

  //method declaration
  DataType getDataType();

  //method declaration
  FieldType getPropertyType();
}

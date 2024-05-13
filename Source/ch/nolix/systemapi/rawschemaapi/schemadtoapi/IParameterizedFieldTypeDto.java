//package declaration
package ch.nolix.systemapi.rawschemaapi.schemadtoapi;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.fieldapi.mainapi.FieldType;

//interface
public interface IParameterizedFieldTypeDto {

  //method declaration
  DataType getDataType();

  //method declaration
  FieldType getFieldType();
}

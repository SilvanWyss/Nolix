//package declaration
package ch.nolix.systemapi.rawschemaapi.schemadtoapi;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

//interface
public interface IParameterizedFieldTypeDto {

  //method declaration
  DataType getDataType();

  //method declaration
  ContentType getFieldType();
}
